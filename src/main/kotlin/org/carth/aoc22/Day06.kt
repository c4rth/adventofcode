package org.carth.aoc22

import org.carth.common.Puzzle

class Day06(private val data: String) : Puzzle<String, String>() {

    override fun solvePartOne(): String = findUnique(4).toString()

    override fun solvePartTwo(): String = findUnique(14).toString()

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