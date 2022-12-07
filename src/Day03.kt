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
                .sumOf { getItemPriority(it) }
        }

    fun part2(input: List<String>) =
        input
            .map { it.toCharArray() }
            .chunked(3) { group ->
                group[0]
                    .map {
                        if (group[1].contains(it) && group[2].contains(it)) it else '%'
                    }
                    .filter { it != '%' }
                    .distinct()
                    .sumOf { getItemPriority(it) }
            }
            .sum()

    val testInput = readInputLines("Day03_test")
    println(part1(testInput))
    check(part1(testInput) == 157)
    check(part2(testInput) == 70)

    val input = readInputLines("Day03")
    println(part1(input))
    println(part2(input))
}

fun getItemPriority(char: Char) : Int = if (char.isLowerCase()) char.code - 96 else char.code - 64 + 26
