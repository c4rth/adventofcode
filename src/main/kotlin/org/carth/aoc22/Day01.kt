package org.carth.aoc22

import org.carth.common.Puzzle

class Day01(private val data: String) : Puzzle<String, String>() {

    override fun solvePartOne() = getCaloriesList().max().toString()

    override fun solvePartTwo() = getCaloriesList().take(3).sum().toString()

    private fun getCaloriesList(): List<Int> {
        return data.split(System.lineSeparator() + System.lineSeparator())
            .map { it.lines().sumOf(String::toInt) }
            .sortedDescending()
    }

}