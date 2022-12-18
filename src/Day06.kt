fun main() {
    fun part1(input: String): Int {
        val buffer = ArrayDeque<Char>()
        val monkey = input
            .toCharArray()
            .indexOfFirst { element ->
                buffer.addLast(element)
                if (buffer.size > 4) buffer.removeFirst()
                if (buffer.size > 3) return@indexOfFirst (buffer.distinct().size > 3)

                return@indexOfFirst false
            }
        return monkey + 1
    }

    fun part2(input: List<String>) {

    }

    check(part1("bvwbjplbgvbhsrlpgdmjqwftvncz") == 5)
    check(part1("nppdvjthqldpwncqszvftbrmjlhg") == 6)
    check(part1("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg") == 10)
    check(part1("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw") == 11)
//    check(part2(testInput) == 12)

    val input = readInputText("Day06")
    println(part1(input))
//    println(part2(input))
}