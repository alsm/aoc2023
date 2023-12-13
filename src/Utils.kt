import java.math.BigInteger
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readLines
import java.util.Queue
import java.util.PriorityQueue
import kotlin.io.path.readText
import kotlin.math.absoluteValue

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = Path("src/$name.txt").readLines()

fun readInputGroups(name: String) = Path("src/$name.txt").readText().split("\n\n").map { it.split("\n") }

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
        .toString(16)
        .padStart(32, '0')

fun gcd(a: Long, b: Long): Long {
    if (b == 0L) return a
    return gcd(b, a % b)
}

fun lcm(a: Long, b: Long): Long {
    return a / gcd(a, b) * b
}

fun gcd(a: Int, b: Int): Int {
    if (b == 0) return a
    return gcd(b, a % b)
}

fun lcm(a: Int, b: Int): Int {
    return a / gcd(a, b) * b
}

fun lcmOfList(num: List<Long>): Long {
    var result = num[0]
    for (i in 1 until num.size) {
        result = lcm(result, num[i])
    }
    return result
}

inline fun <reified T> Array<Array<T>>.transpose(): Array<Array<T>> {
    return Array(this[0].size) { i -> Array(this.size) { j -> this[j][i] } }
}

/**
 * The cleaner shorthand for printing output.
 */
fun Any?.println() = println(this)

data class Point(var x: Long, var y: Long)

fun Point.loc(): Point = this

operator fun Point.plus(other: Point) = Point(this.x + other.x, this.y + other.y)

operator fun Point.minus(other: Point) = Point(this.x - other.x, this.y - other.y)

fun Point.mDistance(other: Point): Long = (this.x - other.x).absoluteValue + (this.y - other.y).absoluteValue

interface Loc {
    val loc: Point
    fun connectsTo(): List<Point>
}

class Graph(val adj: Map<Point, List<Loc>>) {
    fun shortestPath(start: Point, end: Point): Int {
        val seen = mutableSetOf<Loc>()
        val q: Queue<Loc> = PriorityQueue()

        while (q.isNotEmpty()) {
            val np = q.poll()

            if (np !in seen) {
                seen += np
                val neighbors = np.connectsTo()
                        .filter { it -> adj[it]!!.map { it.loc }.any { it == np.loc } }
//                if (neighbors.any { isGoal(it) }) return nextPoint.cost + 1
//                queue.addAll(neighbors.map { PathCost(it, nextPoint.cost + 1) })
            }
        }
        throw IllegalStateException("No valid path from $start to goal")
    }
}

fun List<Point>.pairs(): Sequence<Pair<Point, Point>> = sequence {
    for (i in 0 until size - 1)
        for (j in i + 1 until size)
            yield(get(i) to get(j))
}