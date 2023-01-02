package org.carth.aoc22

import org.carth.common.Puzzle


class Day20(input: String) : Puzzle<Long, Long>() {
    private val data = input.lines()
    override fun solvePartOne() = solve(1, 1)

    override fun solvePartTwo() = solve(811589153L, 10)

    private fun solve(key: Long, times: Int): Long {
        val encrypted = data.withIndex().map { IndexedValue(it.index, it.value.toLong() * key) }.toMutableList()
        val size = encrypted.size
        repeat(times) {
            for (i in 0 until size) {
                val (idx, number) = encrypted.withIndex().first { indexed ->
                    indexed.value.index == i
                }
                encrypted.removeAt(idx)
                val newIdx = (idx + number.value).mod(size - 1)
                encrypted.add(newIdx, number)

            }
        }
        val index0 = encrypted.indexOfFirst { it.value == 0L }
        return encrypted[(index0 + 1000) % size].value + encrypted[(index0 + 2000) % size].value + encrypted[(index0 + 3000) % size].value
    }

}