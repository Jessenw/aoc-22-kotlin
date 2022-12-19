fun main() {
    fun part1(input: List<String>): Int {
        return input.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    val testInput = readInputLines("Day07_test")
    check(part1(testInput) == 0)
//    check(part2(testInput) == "MCD")

    val input = readInputLines("Day07")
    println(part1(input))
    println(part2(input))
}