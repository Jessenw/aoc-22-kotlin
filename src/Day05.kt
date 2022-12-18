import java.io.File


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

fun main() {
    fun part1(stacks: List<ArrayDeque<String>>, moves: String): String {

        return ""
    }

    fun part2(input: List<String>) {

    }

    val file = File("input", "Day05_test.txt").readText()
    val splitFile = file.split("\n\n")
    check(part1(buildStacks(splitFile[0].lines()), splitFile[1]) == "CMZ")
//    check(part2(testInput) == 12)
//
//    val input = readInputLines("Day03")
//    println(part1(input))
//    println(part2(input))
}
