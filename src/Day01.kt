fun main() {
    fun part1(input: String): Int {
        // Split into each elves' list of carried calories
        val carriedCalories = input.split("\n\n")

        var maxCalories = -1
        carriedCalories.forEach { carried ->
            val calorieItems = carried.split("\n")

            var totalCalories = 0
            calorieItems.forEach {
                if (it != "") totalCalories += it.toInt()
            }
            if (totalCalories > maxCalories) maxCalories = totalCalories
        }
        return maxCalories
    }

    fun part2(input: String) : Int {
        // Find the total amount of calories each elf if carrying
        val totalCalories = input.split("\n\n")
            .map { bag ->
                bag.lines().sumOf {
                    it.toIntOrNull() ?: 0 // Handle empty string
                }
            }

        // Order sums and then pick the 3 highest to sum
        return totalCalories
            .sortedDescending()
            .toIntArray()
            .take(3)
            .sum()
    }

    val testInput = readInputText("Day01_test")
    println(part1(testInput))
    println(part2(testInput))
    check(part1(testInput) == 24000)
    check(part2(testInput) == 45000)

    val input = readInputText("Day01")
    println(part1(input))
    println(part2(input))
}
