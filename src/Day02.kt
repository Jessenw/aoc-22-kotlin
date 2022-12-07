import java.io.File

fun main() {
    fun part1(input: List<String>) =
        input
            .sumOf {
                val split = it.split(" ")
                val round = Pair(scoreForPlay(split[0]), scoreForPlay(split[1]))
                val roundPoints =
                    if (round.first == round.second) 3
                    else if (round.second == 1 && round.first == 3
                        || round.second == 3 && round.first == 2
                        || round.second == 2 && round.first == 1) {
                        6
                    }
                    else {
                        0
                    }
                roundPoints + round.second
            }

    fun part2(input: List<String>): Int {
        return input.size
    }

    val testInput = readInputLines("Day02_test")
    println(part1(testInput))
    println(part2(testInput))
    check(part1(testInput) == 15)
//    check(part2(testInput) == 45000)

    val input = readInputLines("Day02")
    println(part1(input))
//    println(part2(input))
}

fun scoreForPlay(play: String) =
    when (play) {
        "A", "X" -> 1 // Rock
        "B", "Y" -> 2 // Paper
        "C", "Z" -> 3 // Scissors
        else -> 0
    }