import kotlin.math.abs

fun main() {
    data class Point(val x: Int, val y: Int) {
        fun adjacent(other: Point): Boolean {
            return (abs(other.x - this.x) <= 1) and (abs(other.y - this.y) <= 1)
        }
    }

    data class PartNum(val value: Int, val loc: List<Point>)

    data class Symbol(val char: String, val loc: Point)

    val partNums = ArrayList<PartNum>()
    val symbols = ArrayList<Symbol>()

    fun buildLists(input: List<String>) {
        input.forEachIndexed { y, l ->
            Regex("""\d+|[^\w\d\.]+""").findAll(l).forEach { r ->
                r.groups.filterNotNull().forEach { g ->
                    if (g.value[0].isDigit())
                        partNums.add(PartNum(g.value.toInt(), g.range.map { x -> Point(x, y) }))
                    else
                        symbols.add(Symbol(g.value, Point(g.range.first, y)))
                }
            }
        }
    }

    fun part1(): Int {
        return partNums.filter { p ->
            symbols.any { s -> p.loc.any { it.adjacent(s.loc) } }
        }.sumOf { p -> p.value }
    }

    fun part2(): Int {
        return symbols.filter { g ->
            (g.char == "*") and (partNums.count { p ->
                p.loc.any { it.adjacent(g.loc) }
            } == 2)
        }.sumOf { g ->
            partNums.filter { p ->
                p.loc.any { it.adjacent(g.loc) }
            }.map { it.value }.reduce(Int::times)
        }
    }

    buildLists(readInput("Day03"))
    part1().println()
    part2().println()
}