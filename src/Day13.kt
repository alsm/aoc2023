fun main() {
    fun findMirror(pat: Array<Array<Char>>): Int {
        return pat.mapIndexedNotNull { i, l ->
            if (i < pat.size - 1 && pat[i + 1].contentEquals(l)) i else null
        }[0]
    }

    fun part1(input: List<List<String>>): Long {
        val patterns = input.map { p -> p.map { it.toCharArray().toTypedArray() }.toTypedArray() }

        patterns.forEach {
            findMirror(it).println()
        }

        return 1
    }

    fun part2(input: List<String>): Long {
        return 1
    }

    val testInput = readInputGroups("Day13_test")
    part1(testInput)

//    val input = readInput("Day13")
//    part1(input).println()
//    part2(input).println()
}