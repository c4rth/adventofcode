package org.carth.aoc20

import org.carth.common.DayTests
import org.carth.common.Puzzle.Part
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test

class Day19Tests : DayTests<Day19>(Day19::class) {
    @Test
    @Order(1)
    fun solvePartOneSample1() = solve(Part.ONE, Type.TEST, "1", expected = 2)
    @Test
    @Order(2)
    fun solvePartOneSample2() = solve(Part.ONE, Type.TEST, "2", expected = 3)

    @Test
    @Order(3)
    fun solvePartOne() = solve(Part.ONE, Type.INPUT, expected = 213)

    @Test
    @Order(4)
    fun solvePartTwoSample2() = solve(Part.TWO, Type.TEST,  "2", expected = 12)

    @Test
    @Order(6)
    fun solvePartTwo() = solve(Part.TWO, Type.INPUT, expected = 325)
}