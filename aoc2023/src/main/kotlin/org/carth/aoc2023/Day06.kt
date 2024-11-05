package org.carth.aoc2023

import org.carth.common.Puzzle

class Day06(input: String) : Puzzle<Long, Long>() {

    private val data = input.split(System.lineSeparator()).map { line ->
        line.split(":")[1].split(" ").filter { it.isNotEmpty() }.map { it.toLong() }
    }

    override fun solvePartOne(): Long {
        val (times, records) = data
        return times.mapIndexed { index, time -> countRaces(time, records[index]) }.reduce { acc, races -> acc * races }
    }

    override fun solvePartTwo(): Long {
        val time = data[0].joinToString("").toLong()
        val record = data[1].joinToString("").toLong()
        return countRaces(time, record)
    }

    private fun countRaces(time: Long, distance: Long): Long {
        var total = 0L
        (1 ..< time).forEach {
            if (it * (time - it) > distance) total += 1L
        }
        return total
    }
}