fun main() {
    class Grid(input: List<String>) {
        var points = input.mapIndexed { y, line ->
            line.mapIndexed { x, c ->
                if (c == '#') Point(x.toLong(), y.toLong()) else null
            }
        }.flatten().filterNotNull()
        var maxX = (input.first().length - 1).toLong()
        var maxY = (input.size - 1).toLong()
    }

    fun Grid.expand(s: Long): Grid {
        (0..maxY).mapNotNull { y ->
            if (points.any { it.y == y }) null else y
        }.forEachIndexed { i, row ->
            points.forEach { p -> if (p.y > (i * (s - 1)) + row) p.y += s - 1 }
        }

        (0..maxX).mapNotNull { x ->
            if (points.any { it.x == x }) null else x
        }.forEachIndexed { i, col ->
            points.forEach { p -> if (p.x > (i * (s - 1)) + col) p.x += s - 1 }
        }

        return this
    }

    fun part1(input: List<String>): Long {
        val g = Grid(input).expand(2)
        return g.points.pairs().sumOf { (a, b) ->
            a.mDistance(b)
        }
    }

    fun part2(input: List<String>): Long {
        val g = Grid(input).expand(1000000)
        return g.points.pairs().sumOf { (a, b) ->
            a.mDistance(b)
        }
    }

    val input = readInput("Day11")
    part1(input).println()
    part2(input).println()
}