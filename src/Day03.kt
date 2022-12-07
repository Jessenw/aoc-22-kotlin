fun main() {
    fun part1(input: List<String>): Int =
        input.sumOf { line ->
            val compartments = line.chunked(line.length / 2)
                .map { it.toCharArray() }

            compartments[0]
                .map {
                    // Look for duplicate between compartments.
                    // "%" indicates no duplicate
                    if (compartments[1].contains(it)) it else '%'
                }
                .filter { it != '%' }
                .distinct()
                .sumOf {
                    // Get UTF-16 code, offset to start at 0 and then apply our
                    // own offset depending on the chars case:
                    // Lowercase 1 -> 16, Uppercase 27 -> 52
                    if (it.isLowerCase()) it.code - 96 else it.code - 64 + 26
                }
        }

    fun part2(input: List<String>) {

    }

    val testInput = readInputLines("Day03_test")
    println(part1(testInput))
    check(part1(testInput) == 157)
//    check(part2(testInput) == 12)

    val input = readInputLines("Day03")
    println(part1(input))
//    println(part2(input))
}