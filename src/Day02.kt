fun main() {
    fun part1(input: List<String>) =
        input
            .sumOf {
                val split = it.split(" ")
                val opponentPlay = scoreForPlay(split[0])
                val myPlay = scoreForPlay(split[1])

                val roundPoints =
                    if (opponentPlay == myPlay) 3
                    else if (myPlay == 1 && opponentPlay == 3
                        || myPlay == 3 && opponentPlay == 2
                        || myPlay == 2 && opponentPlay == 1) 6
                    else 0
                roundPoints + myPlay
            }

    fun part2(input: List<String>) =
        input
            .sumOf {
                val split = it.split(" ")
                val opponentPlay = scoreForPlay(split[0])
                val outcome = scoreForPlay(split[1])

                if (outcome == 1)
                    if (opponentPlay - 1 < 1) 3 else opponentPlay - 1
                else if (outcome == 2)
                    opponentPlay + 3
                else
                    (if (opponentPlay + 1 > 3) 1 else opponentPlay + 1) + 6
            }

    val testInput = readInputLines("Day02_test")
    check(part1(testInput) == 15)
    check(part2(testInput) == 12)

    val input = readInputLines("Day02")
    println(part1(input))
    println(part2(input))
}

fun scoreForPlay(play: String) =
    when (play) {
        "A", "X" -> 1 // Rock, lose
        "B", "Y" -> 2 // Paper, draw
        "C", "Z" -> 3 // Scissors, win
        else -> 0
    }