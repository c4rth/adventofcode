package org.carth.aoc20

import org.carth.common.Puzzle

class Day09(input: String) : Puzzle<Long, Long>() {

    private val data = input.lines().map { it.toLong() }

    override fun solvePartOne() = 0L

    override fun solvePartTwo() = 0L

    fun solvePartOne(preamble: Int): Long {
        var index = preamble
        data.windowed(preamble).forEach { subdata ->
            if (!getSums(subdata).contains(data[index]))
                return data[index]
            index++
        }
        return 0
    }

    fun solvePartTwo(preamble: Int): Long {
        val num = solvePartOne(preamble)
        data.windowed(preamble).forEach { subdata ->
            getAllSums(subdata).forEach { list ->
                if (list.sum() == num) {
                    return (list.min() + list.max())
                }
            }
        }
        return 0
    }

    private fun getSums(list: List<Long>): List<Long> {
        val res = mutableListOf<Long>()
        for (i in list.indices) {
            list.drop(i + 1).forEach { res.add(list[i] + it) }
        }
        return res
    }

    private fun getAllSums(list: List<Long>): List<List<Long>> {
        val res = mutableListOf<MutableList<Long>>()
        for (i in list.indices) {
            for (j in 2..list.size - i) {
                val subRes = mutableListOf<Long>()
                subRes.addAll(list.drop(i).take(j))
                res.add(subRes)
            }
        }
        return res
    }
}