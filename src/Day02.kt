fun main() {
    /*
    A,X = Rock -> 1
    B, Y = Paper -> 2
    C, Z = Scissors -> 3
    0 = Lost
    3 = Draw
    6 = Won
     */
    fun part1(input: List<String>) =
        input
            .map { line ->
                val split = line.split(" ")
                Pair(scoreForPlay(split[0]), scoreForPlay(split[1]))
            }.sumOf { round ->
                val roundPoints =
                    if (round.first == round.second) 3
                    else if (round.first < round.second) 6
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
//    println(part2(testInput))
    check(part1(testInput) == 15)
//    check(part2(testInput) == 45000)

//    val input = readInputText("Day01")
//    println(part1(input))
//    println(part2(input))
}

fun scoreForPlay(play: String) =
    when (play) {
        "A", "X" -> 1 // Rock
        "B", "Y" -> 2 // Paper
        "C", "Z" -> 3 // Scissors
        else -> 0
    }
