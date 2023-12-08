fun main() {
    fun <T> Sequence<T>.repeat() =
            generateSequence(this) { it }.flatten()

    data class Node(val left: String, val right: String, var repeat: Int = 0) {
        fun next(d: Char): String {
            return when (d) {
                'L' -> this.left
                else -> this.right
            }
        }
    }

    fun part1(cmds: CharArray, nodes: Map<String,Node>): Int {
        var currNode = nodes["AAA"]!!

        for ((i, v) in cmds.asSequence().repeat().withIndex().iterator()) {
            val nextNode = currNode.next(v)
            if (nextNode == "ZZZ") return i + 1
            currNode = nodes[nextNode]!!
        }

        return 0
    }

    fun part2(cmds: CharArray, nodes: Map<String,Node>): Long {
        var currNodes = nodes.filter { e -> e.key[2] == 'A' }.values
        val repeats = currNodes.toMutableList()
        
        for ((i, v) in cmds.asSequence().repeat().withIndex().iterator()) {
            val nextNodes = currNodes.map { it.next(v) }
            nextNodes.forEachIndexed { j, n -> if (n[2] == 'Z') repeats[j].repeat = i + 1 }
            if (repeats.all { it.repeat != 0 }) return lcmOfList(repeats.map { it.repeat.toLong() })
            currNodes = nextNodes.map { nodes[it]!! }
        }

        return 0
    }

    val input = readInput("Day08")
    val cmds = input.first().toCharArray() //
    val nodes = input.drop(2).associate { l ->
        val s = l.split(Regex("""\W+"""))
        Pair(s[0], Node(s[1], s[2]))
    }
    part1(cmds, nodes).println()
    part2(cmds, nodes).println()
}