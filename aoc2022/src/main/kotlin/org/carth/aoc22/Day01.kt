package org.carth.aoc22

import org.carth.common.Puzzle

class Day01(private val data: String) : Puzzle<Int, Int>() {

    override fun solvePartOne() = getCaloriesList().max()

    override fun solvePartTwo() = getCaloriesList().take(3).sum()

    private fun getCaloriesList(): List<Int> {
        return data.split(System.lineSeparator() + System.lineSeparator())
            .map { it.lines().sumOf(String::toInt) }
            .sortedDescending()
    }

}