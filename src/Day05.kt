fun main() {
    fun part1(input: List<String>): String {
        val stacks: List<ArrayDeque<String>> = buildStacks(input[0].lines())
        val moves: List<List<Int>> = buildMoves(input[1].lines())

        moves.forEach {
            for (i in 0 until it[0])
                stacks[it[2] - 1].addLast(stacks[it[1] - 1].removeLast())
        }

        return stacks.joinToString("") { it.removeLast() }
    }

    fun part2(input: List<String>): String {
        val stacks: List<ArrayDeque<String>> = buildStacks(input[0].lines())
        val moves: List<List<Int>> = buildMoves(input[1].lines())

        moves.forEach {
            val temp = mutableListOf<String>()
            for (i in 0 until it[0]) {
                temp.add(stacks[it[1] - 1].removeLast())
            }
            temp.reversed().forEach { t ->
                stacks[it[2] - 1].addLast(t)
            }
        }

        return stacks.joinToString("") { it.removeLast() }
    }

    val testInput = readInputText("Day05_test").split("\n\n")
    check(part1(testInput) == "CMZ")
    check(part2(testInput) == "MCD")

    val input = readInputText("Day05").split("\n\n")
    println(part1(input))
    println(part2(input))
}

fun buildStacks(lines: List<String>): List<ArrayDeque<String>> {
    val stackCount = lines
        .dropWhile { it.contains("[") }
        .first()
        .split(" ")
        .filter { it.isNotBlank() }
        .maxOf { it.toInt() }

    val stacks = MutableList(stackCount) { ArrayDeque<String>() }

    for (i in 0..stackCount) {
        lines
            .dropLast(1)
            .map {
                it
                    .chunked(1)
                    .toMutableList()
                    .drop(1)
            }
            .forEach {
                val index = i * 4
                if (index <= it.size) {
                    val element = it[index]
                    if (element != " ") {
                        // These are "stacks" but we add as a queue since we read lines top to bottom
                        stacks[i].addFirst(element)
                    }
                }
            }
    }

    return stacks
}

fun buildMoves(lines: List<String>) =
    lines
        .map { line ->
            line.split(" ")
                .filterIndexed { index, _ ->
                    index % 2 != 0
                }
                .map { it.toInt() }
        }
