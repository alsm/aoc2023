fun main() {
    fun part1(input: List<Pair<Set<Int>, Set<Int>>>): Int {
        return input.map { it.first.intersect(it.second) }
                .filter { it.isNotEmpty() }
                .sumOf { 1 shl it.count() } / 2
    }

    fun part2(input: List<Pair<Set<Int>, Set<Int>>>): Int {
        val count = (0..input.size).associateWith { 1 }.toMutableMap()
        input.forEachIndexed { i, p ->
            (i + 1..i + p.first.intersect(p.second).count()).forEach {
                count[it] = count[it]!! + count[i]!!
            }
        }

        return count.filter { e -> e.key < input.size }.values.sum()
    }

    fun parseGame(input: List<String>): List<Pair<Set<Int>, Set<Int>>> {
        return input.map { line ->
            line.substringAfter(": ").split(" | ").let { Pair(it[0], it.getOrNull(1) ?: "") }
        }.map { p ->
            Pair(
                    p.first.trim().split(Regex("\\s+")).map(String::toInt).toSet(),
                    p.second.trim().split(Regex("\\s+")).map(String::toInt).toSet(),
            )
        }
    }

    val input = readInput("Day04")
    part1(parseGame(input)).println()
    part2(parseGame(input)).println()
}