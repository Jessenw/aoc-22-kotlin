fun main() {
    fun part1(input: String) = distinctIndex(input, 4)

    fun part2(input: String) = distinctIndex(input, 14)

    check(part1("bvwbjplbgvbhsrlpgdmjqwftvncz") == 5)
    check(part2("mjqjpqmgbljsphdztnvjfqwrcgsmlb") == 19)

    val input = readInputText("Day06")
    println(part1(input))
    println(part2(input))
}

fun distinctIndex(input: String, nElements: Int): Int {
    val buffer = ArrayDeque<Char>()
    return input
        .toCharArray()
        .indexOfFirst {
            buffer.addLast(it)
            if (buffer.size > nElements) buffer.removeFirst()
            if (buffer.size > nElements - 1) return@indexOfFirst (buffer.distinct().size > nElements - 1)

            return@indexOfFirst false
        } + 1
}