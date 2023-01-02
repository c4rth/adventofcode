package org.carth.aoc22

import org.carth.common.Puzzle

class Day06(private val data: String) : Puzzle<Int, Int>() {

    override fun solvePartOne(): Int = findUnique(4)

    override fun solvePartTwo(): Int = findUnique(14)

    private fun findUnique(window: Int): Int {
        data.windowed(window).forEachIndexed { index, packet ->
            if (packet.allUnique()) {
                return index + window
            }
        }
        return 0
    }

    private fun String.allUnique(): Boolean = all(hashSetOf<Char>()::add)
}