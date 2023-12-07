fun main() {
    data class Hand(val cards: List<Int>, val bid: Int) : Comparable<Hand> {
        fun score(): Int {
            var h = cards.sortedDescending().groupBy { it }.values.sortedByDescending { it.size }
            if (h.any { it.contains(1) } && h.size > 1) {
                val (jokers, rem) = h.partition { it.contains(1) }
                h = listOf(rem[0]+jokers[0]) + rem.drop(1)
            }
                
            return when {
                h.size == 1 -> 7
                h.size == 2 && h[0].size == 4 && h[1].size == 1 -> 6
                h.size == 2 && h[0].size == 3 && h[1].size == 2 -> 5
                h.size == 3 && h[0].size == 3 -> 4
                h.size == 3 && h[0].size == 2 -> 3
                h.size == 4 -> 2
                else -> 1
            }
        }
        override fun compareTo(other: Hand): Int =
            compareValuesBy(this, other,
                            { it.score() },
                            { it.cards[0] },
                            { it.cards[1] },
                            { it.cards[2] },
                            { it.cards[3] },
                            { it.cards[4] })
    }
    
    fun score(input: List<String>, scores: Map<Char, Int>) : Int {
        return input.map { l -> l.split(" ") }.map { e ->
            Hand(e[0].toCharArray().map { c -> c.toString().toIntOrNull() ?: scores[c]!! }, e[1].toInt())
        }
            .sorted()
            .foldIndexed(0) { i, acc, h -> acc + (i + 1) * h.bid }
    }

    fun part1(input: List<String>): Int {
        return score(input, mapOf('T' to 10, 'J' to 11, 'Q' to 12, 'K' to 13, 'A' to 14))
    }

    fun part2(input: List<String>): Int {
        return score(input, mapOf('T' to 10, 'J' to 1, 'Q' to 12, 'K' to 13, 'A' to 14))
    }

    val input = readInput("Day07")
    part1(input).println()
    part2(input).println()
}