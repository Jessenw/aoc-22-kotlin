data class Node<T>(
    val value: T,
    val children: MutableList<Node<T>> = mutableListOf()
)

fun Node<Directory>.calculateSize(): Int =
    children.sumOf {
        it.calculateSize()
    } + value.fileSize()

data class File(
    val name: String,
    val size: Int
)

data class Directory(
    val name: String,
    val files: MutableList<File> = mutableListOf()
) {
    fun fileSize() = files.sumOf { it.size }
}

fun deleteCandidates(
    currentNode: Node<Directory>,
    limit: Int,
    list: MutableList<Int> = mutableListOf(),
    filter: (Int, Int) -> Boolean
) : List<Int> {
    val size = currentNode.calculateSize()

    if (filter(size, limit))
        list.add(size)

    currentNode.children.forEach {
        deleteCandidates(it, limit, list, filter)
    }

    return list.toList()
}

fun buildTree(input: List<String>): Node<Directory> {
    val root = Node(Directory("/"))
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
                            currentDirectory.children.add(Node(Directory(lsSplit[1])))
                        else
                            currentDirectory.value.files.add(File(lsSplit[1], lsSplit[0].toInt()))

                        inputIndex++
                    }
                }
            }
        }
    }

    return root
}

fun findDirectory(currentNode: Node<Directory>, path: MutableList<String>): Node<Directory> {
    val copiedPath = path.toMutableList() // Create a clone of the path
    val dirName = copiedPath.removeFirst()
    val nextNode =
        currentNode.children
            .firstOrNull {
                it.value.name == dirName
            } ?: currentNode

    return if (copiedPath.size == 0) nextNode else findDirectory(nextNode, copiedPath)
}

fun main() {

    fun part1(input: List<String>) =
        deleteCandidates(buildTree(input), 100000) { size, limit ->
            size <= limit
        }.sumOf { it }

    fun part2(input: List<String>): Int {
        val root = buildTree(input)

        // Storage required = update_size - (disk_space - space_used)
        val storageRequired = 30000000 - (70000000 - root.calculateSize())

        return deleteCandidates(root, storageRequired) { size, limit ->
            size > limit
        }.minOf { it }
    }

    val testInput = readInputLines("Day07_test")
    check(part1(testInput) == 95437)
    check(part2(testInput) == 24933642)

    val input = readInputLines("Day07")
    println(part1(input))
    println(part2(input))
}
