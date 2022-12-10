fun main() {
    fun part1(input: List<String>): Int =
        input.map { pair ->
            val sections =
                pair.split(",")
                    .map { ranges ->
                        ranges.split("-")
                            .map { it.toInt() }
                    }
                    .map { Pair(it[0], it[1]) }

            if (rangeInRange(sections[0], sections[1])
                || rangeInRange(sections[1], sections[0])) 1
                else 0
        }.sum()

    fun part2(input: List<String>) {

    }

    val testInput = readInputLines("Day04_test")
    check(part1(testInput) == 3)
//    check(part2(testInput) == 12)

    val input = readInputLines("Day04")
    check(part1(input) == 413)
//    println(part2(input))
}
fun rangeInRange(a: Pair<Int, Int>, b: Pair<Int, Int>) =
    a.first >= b.first && a.second <= b.second
