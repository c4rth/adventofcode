package org.carth.aoc22

import org.carth.common.Puzzle
import kotlin.math.pow

class Day25(input: String) : Puzzle<String, String>() {

    private val data = input.lines()

    override fun solvePartOne(): String {
        val number = data.sumOf { snafu -> snafu.toDecimal() }
        return number.toSnafu()
    }

    override fun solvePartTwo(): String {
        return "done"
    }

    private var snafu = listOf('=', '-', '0', '1', '2')

    private fun String.toDecimal(): Long =
        this.reversed().mapIndexed { index, c ->
            (5.0.pow(index) * (snafu.indexOf(c) - 2L)).toLong()
        }.sum()

    private fun Long.toSnafu(): String {
        if (this == 0L) return "0"
        var out = ""
        var num = this
        while (num > 0) {
            val rem = (num + 2).mod(5)
            num = (num + 2) / 5
            out += snafu[rem]
        }
        return out.reversed()
    }
}