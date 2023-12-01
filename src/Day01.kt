fun main() {
    fun part1(input: List<String>): Int {
        return input.map {
            it.toCharArray().filter { c -> c.isDigit() }
        }.fold(0) { acc, x -> acc + "${x.first()}${x.last()}".toInt() }
    }

    fun replaceStringDigits(s: String): String {
        val digits = listOf(
            "one" to "1",
            "two" to "2",
            "three" to "3",
            "four" to "4",
            "five" to "5",
            "six" to "6",
            "seven" to "7",
            "eight" to "8",
            "nine" to "9",
            "ten" to "10",
            )
        var ret = ""
        s.forEachIndexed { i, c ->
            when {
                c.isDigit() -> ret += c
                else -> digits.forEach { (x,y) ->
                    if (i+x.length-1 < s.length && s.substring(i..<i+x.length).contains(x))
                        ret += y
                }
            }
        }
        return ret
    }

    fun part2(input: List<String>): Int {
        return part1(input.map { replaceStringDigits(it) })
    }

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
