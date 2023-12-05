fun main() {
    var seeds = listOf<Long>()
    var maps  = listOf<List<Triple<Long, Long, Long>>>()

    fun part1(): Long {
        return seeds.minOf {
            var s = it
            maps.forEach { m ->
                m.filter { (_, src, r) -> (src..src+r).contains(s)}
                    .toList()
                    .firstOrNull()?.let { i -> s = i.first + (s - i.second) }
            }
            s
        }
    }
    
    fun part2(): Long {
        val initVals = seeds.chunked(2).map { (a,b) ->a.rangeTo(a+b)}
        for (i in 1..Long.MAX_VALUE) {
            var s = i
            maps.reversed().forEach { m ->
                m.filter { (dst, _, r) -> (dst..dst+r).contains(s)}
                    .toList()
                    .firstOrNull()?.let { s = it.second + (s - it.first) }
            }
            if (initVals.any { it.contains(s) }) return i
        }
        return 0
    }

    fun processInput(input: List<String>) {
        seeds = input[0].substringAfter("seeds: ").split(" ").map(String::toLong)
        maps = input.drop(2)
                .joinToString("\n")
                .split(Regex(""".*\smap:\n"""))
                .filter { it.isNotEmpty() }
                .map { l ->
                    l.trim()
                            .split("\n")
                            .map { nums -> nums.split(" ").map(String::toLong) }
                            .map { n ->
                                Triple(n[0], n[1], n[2])
                            }
                }
    }

    val input = readInput("Day05")
    processInput(input)

    part1().println()
    part2().println()
}