fun main() {
    data class Oasis(val initial: MutableList<Int>) {
        var seqs = mutableListOf<MutableList<Int>>()
        fun gen() {
            seqs.add(initial)
            do {
                val diffs = seqs.last().windowed(2, 1).map { (a, b) -> b - a }
                seqs.add(diffs.toMutableList())
            } while (diffs.any{it != 0})

        }

        fun next(): Int{
            if (seqs.isEmpty()) gen()
            seqs.last().add(seqs.last().last())
            seqs.reversed().windowed(2,1).forEach{ (a,b) ->
                b.add(b.last()+a.last())
            }
            return seqs.first().last()
        }
        
        fun prev(): Int {
            if (seqs.isEmpty()) gen()
            seqs.last().add(0, seqs.last().first())
            seqs.reversed().windowed(2,1).forEach{ (a,b) ->
                b.add(0, b.first()-a.first())
            }
            return seqs.first().first()
        }
    }

    fun part1(input: List<String>): Int {
        val seqs = input.map { it.split(" ").map(String::toInt) }.map { Oasis(it.toMutableList()) }
        return seqs.sumOf{ it.next() }
    }

    fun part2(input: List<String>): Int {
        val seqs = input.map { it.split(" ").map(String::toInt) }.map { Oasis(it.toMutableList()) }
        return seqs.sumOf{ it.prev() }
    }

    val input = readInput("Day09")
    part1(input).println()
    part2(input).println()
}