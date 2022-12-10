fun main() {
    fun part1(input: List<String>): Int =
        input.map { pair ->
            val sections = pair.split(",")
            val section1 = sections[0].split("-")
                .map { it.toInt() }
            val section2 = sections[1].split("-")
                .map { it.toInt() }

            // Check which Elf has the potential to have their sections
            // already covered
            return@map if ((section1[0] >= section2[0] && section1[1] <= section2[1])
                || (section2[0] >= section1[0] && section2[1] <= section1[1])) 1
            else 0
        }
            .sum()

    fun part2(input: List<String>) {

    }

    val testInput = readInputLines("Day04_test")
    check(part1(testInput) == 3)
//    check(part2(testInput) == 12)

    val input = readInputLines("Day04")
    check(part1(input) == 413)
//    println(part2(input))
}