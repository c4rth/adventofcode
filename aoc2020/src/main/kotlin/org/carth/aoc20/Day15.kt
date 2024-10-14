package org.carth.aoc20

import org.carth.common.Puzzle

class Day15(input: String) : Puzzle<Int, Int>() {

    private val data = input.split(",").map { it.toInt() }

    override fun solvePartOne() = solve(2020)

    override fun solvePartTwo() = solve(30000000)

    private fun solve(to: Int): Int {
        val stats = data.mapIndexed { index, i -> i to index + 1 }.toMap().toMutableMap()
        var last = data.last()
        for (turn in data.size until to) {
            val lastSeen = stats[last]
            stats[last] = turn
            last = if (lastSeen == null) 0 else turn - lastSeen
        }
        return last
    }

}
