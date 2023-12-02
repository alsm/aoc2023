fun main() {
    fun inputToGames(input: List<String>): Map<Int, List<Triple<Int, Int, Int>>> {
        return input.associate { line ->
            Pair(
                    line.substringBefore(": ")
                            .substringAfter(" ")
                            .toInt(),

                    line.substringAfter(": ")
                            .split("; ")
                            .map { it ->
                                it.split(", ").associate {
                                    val x = it.split(" ")
                                    Pair(x.last(), x.first().toInt())
                                }
                            }
                            .map {
                                Triple(
                                        it.getOrDefault("red", 0),
                                        it.getOrDefault("green", 0),
                                        it.getOrDefault("blue", 0)
                                )
                            }

            )
        }
    }

    fun part1(input: List<String>): Int {
        return inputToGames(input)
                .filter {
                    it.value.fold(true) { acc, draw ->
                        acc && (draw.first <= 12 && draw.second <= 13 && draw.third <= 14)
                    }
                }.keys.sum()
    }

    fun part2(input: List<String>): Int {
        return inputToGames(input).values.sumOf { it ->
            listOfNotNull(
                    it.maxOfOrNull { it.first },
                    it.maxOfOrNull { it.second },
                    it.maxOfOrNull { it.third },
            ).reduce(Int::times)
        }
    }

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}