package org.carth.aoc20

import org.carth.common.Puzzle

class Day09(input: String) : Puzzle<String, String>() {

    private val data = input.lines().map { it.toLong() }

    override fun solvePartOne() = "dummy"

    override fun solvePartTwo() = "dummy"

    fun solvePartOne(preamble: Int): String {
        var index = preamble
        data.windowed(preamble).forEach { subdata ->
            if (!getSums(subdata).contains(data[index]))
                return data[index].toString()
            index++
        }
        return "notfound"
    }

    fun solvePartTwo(preamble: Int): String {
        val num = solvePartOne(preamble).toLong()
        data.windowed(preamble).forEach { subdata ->
            getAllSums(subdata).forEach { list ->
                if (list.sum() == num) {
                    return (list.min() + list.max()).toString()
                }
            }
        }
        return "notfound"
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