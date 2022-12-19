data class Node<T>(
    val value: T,
    val children: MutableList<Node<T>> = mutableListOf()
)

fun Node<ItemType.Directory>.calculateSize(): Int {
    return children.sumOf {
        it.calculateSize()
    } + value.fileSize()
}

fun deleteCandidates(
    currentNode: Node<ItemType.Directory>,
    limit: Int,
    list: MutableList<Int> = mutableListOf()
) : List<Int> {
    val size = currentNode.calculateSize()
    if (size <= limit)
        list.add(size)

    currentNode.children.forEach {
        deleteCandidates(it, limit, list)
    }

    return list.toList()
}

fun deleteCandidates2(
    currentNode: Node<ItemType.Directory>,
    limit: Int,
    list: MutableList<Int> = mutableListOf()
) : List<Int> {
    val size = currentNode.calculateSize()
    if (size > limit)
        list.add(size)

    currentNode.children.forEach {
        deleteCandidates2(it, limit, list)
    }

    return list.toList()
}

sealed class ItemType(open val name: String) {
    data class File(
        override val name: String,
        val size: Int
    ) : ItemType(name)

    data class Directory(
        override val name: String,
        val files: MutableList<File> = mutableListOf()
    ) : ItemType(name) {
        fun fileSize() = files.sumOf { it.size }
    }
}

fun findDirectory(currentNode: Node<ItemType.Directory>, path: MutableList<String>): Node<ItemType.Directory> {
    val copiedPath = path.toMutableList() // Create a clone of the path
    val dirName = copiedPath.removeFirst()
    val nextNode = currentNode.children.firstOrNull { it.value.name == dirName } ?: currentNode
    return if (copiedPath.size == 0) nextNode else findDirectory(nextNode, copiedPath)
}

fun main() {

    fun part1(input: List<String>): Int {
        val root = Node(ItemType.Directory("/"))
        val currentPath = ArrayDeque<String>().also { it.add(root.value.name) }

        var inputIndex = 0
        while (inputIndex in input.indices) {
            val currentLine = input[inputIndex]
            val tokens = currentLine.split(" ").toMutableList()

            // Commands begin with "$"
            if (tokens.first() == "$") {
                tokens.removeFirst()

                when(tokens.removeFirst()) {
                    "cd" -> {
                        when (val path = tokens.first()) {
                            ".." -> currentPath.removeLast()
                            "/" -> { }
                            else -> currentPath.addLast(path)
                        }
                        inputIndex++
                    }
                    "ls" -> {
                        // Get all files output from ls (until we hit the next command)
                        inputIndex++ // Skip to next line
                        while(inputIndex < input.size && !input[inputIndex].contains("$")) {
                            val lsSplit = input[inputIndex].split(" ")

                            val currentDirectory = findDirectory(root, currentPath)
                            if (lsSplit.first() == "dir")
                                currentDirectory.children.add(Node(ItemType.Directory(lsSplit[1])))
                            else
                                currentDirectory.value.files.add(ItemType.File(lsSplit[1], lsSplit[0].toInt()))

                            inputIndex++
                        }
                    }
                }
            }
        }

        return deleteCandidates(root, 100000).sumOf { it }
    }

    fun part2(input: List<String>): Int {
        val root = Node(ItemType.Directory("/"))
        val currentPath = ArrayDeque<String>().also { it.add(root.value.name) }

        var inputIndex = 0
        while (inputIndex in input.indices) {
            val currentLine = input[inputIndex]
            val tokens = currentLine.split(" ").toMutableList()

            // Commands begin with "$"
            if (tokens.first() == "$") {
                tokens.removeFirst()

                when(tokens.removeFirst()) {
                    "cd" -> {
                        when (val path = tokens.first()) {
                            ".." -> currentPath.removeLast()
                            "/" -> { }
                            else -> currentPath.addLast(path)
                        }
                        inputIndex++
                    }
                    "ls" -> {
                        // Get all files output from ls (until we hit the next command)
                        inputIndex++ // Skip to next line
                        while(inputIndex < input.size && !input[inputIndex].contains("$")) {
                            val lsSplit = input[inputIndex].split(" ")

                            val currentDirectory = findDirectory(root, currentPath)
                            if (lsSplit.first() == "dir")
                                currentDirectory.children.add(Node(ItemType.Directory(lsSplit[1])))
                            else
                                currentDirectory.value.files.add(ItemType.File(lsSplit[1], lsSplit[0].toInt()))

                            inputIndex++
                        }
                    }
                }
            }
        }

        val diskSize = 70000000
        val updateSize = 30000000
        val usedSpace = root.calculateSize()
        val unusedSpace = diskSize - usedSpace
        val storageRequired = updateSize - unusedSpace

        val hm = deleteCandidates2(root, storageRequired)
        val min = hm.minOf { it }
        return min
    }

    val testInput = readInputLines("Day07_test")
    check(part1(testInput) == 95437)
//    check(part2(testInput) == "MCD")

    val input = readInputLines("Day07")
    println(part1(input))
    println(part2(input))
}
