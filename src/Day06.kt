fun main() {
    fun part1(input: List<String>): Int {
        val races = input.map { l -> l.split(Regex("\\s+")).drop(1).map(String::toInt) }
        return races[0].zip(races[1]).map { (r, d) ->
            (0..r).count { i -> (r - i) * i > d }
        }.reduce(Int::times)
    }

    fun part2(input: List<String>): Int {
        val races = input.map { l ->
            l.split(Regex("\\s+")).drop(1).joinToString("").toLong()
        }
        return (0..races[0]).count { i -> (races[0] - i) * i > races[1] }
    }

    val input = readInput("Day06")
    part1(input).println()
    part2(input).println()
}