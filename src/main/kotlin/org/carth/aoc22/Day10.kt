package org.carth.aoc22

import org.carth.common.Puzzle

class Day10(input: String) : Puzzle<String, String>() {
    private val data = input.lines()

    override fun solvePartOne(): String =
        CpuCrt(1).let {
            solve(it)
            it.signalStrength
        }.toString()

    override fun solvePartTwo(): String =
        CpuCrt(2).let {
            solve(it)
            it.display
        }.toString()

    private fun solve(cpu: CpuCrt) {
        data.forEach { line ->
            if (line.startsWith("noop")) {
                cpu.noop()
            } else {
                cpu.add(line.substringAfter(" ").toInt())
            }
        }
    }

    class CpuCrt(private val model: Int) {
        private val crt = MutableList(6 * 40) { '.' }
        private var x = 1
        private var cycle = 0
        var signalStrength = 0
        val display: String
            get() = crt.chunked(40).joinToString("\n") { it.joinToString("") }

        private fun cycle() {
            cycle++
            if (model == 1) {
                if (cycle <= 220 && cycle % 40 == 20)
                    signalStrength += x * cycle
            } else {
                val pixel = (cycle - 1) % 40
                if (pixel in x - 1..x + 1)
                    crt[cycle - 1] = '#'
            }
        }

        fun add(value: Int) {
            cycle()
            cycle()
            x += value
        }

        fun noop() {
            cycle()
        }
    }
}