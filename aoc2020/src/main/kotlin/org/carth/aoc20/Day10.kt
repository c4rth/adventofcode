package org.carth.aoc20

import org.carth.common.Puzzle

class Day10(input: String) : Puzzle<Int, Long>() {

    private val adapters = input.lines().map { it.toInt() }.sorted().toMutableList().also {
        it.add(0, 0)
        it.add(it.max() + 3)
    }

    override fun solvePartOne(): Int {
        val jolts = adapters.windowed(2).map { it[1] - it[0] }
        return jolts.count { it == 1 } * jolts.count { it == 3 }
    }

    override fun solvePartTwo(): Long {
        val pathsByAdapter: MutableMap<Int,Long> = mutableMapOf(0 to 1L)
        adapters.drop(1).forEach { adapter ->
            pathsByAdapter[adapter] = (1..3).sumOf { lookBack ->
                pathsByAdapter.getOrDefault(adapter - lookBack, 0)
            }
        }
        return pathsByAdapter.getValue(adapters.last())
    }
}