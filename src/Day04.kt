fun main() {
    fun part1(input: List<String>) =
        input.map { pair ->
            val sections =
                processRangePair(pair)
                    .map { Pair(it[0], it[1]) }

            if (rangeInRange(sections[0], sections[1])
                || rangeInRange(sections[1], sections[0])) 1
                else 0
        }.sum()

    fun part2(input: List<String>) =
        input.map { pair ->
            val sections =
                processRangePair(pair)
                    .map {
                        // Generate range of values with given start and end
                        (it[1] downTo it[0]).toList()
                    }
                    .flatten()

            // If we remove duplicates, then there is overlap
            if (sections.distinct().size != sections.size) 1
            else 0
        }.sum()

    val testInput = readInputLines("Day04_test")
    part2(testInput)
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    val input = readInputLines("Day04")
    check(part1(input) == 413)
    check(part2(input) == 806)
}

fun processRangePair(pair: String) =
    pair.split(",")
        .map { ranges ->
            ranges.split("-")
                .map { it.toInt() }
        }

/**
 * Check if a pair (a) of values fully exists within another pair (b)
 * Note: Assumes the first value in a pair is the lower bound
 */
fun rangeInRange(a: Pair<Int, Int>, b: Pair<Int, Int>) =
    a.first >= b.first && a.second <= b.second
