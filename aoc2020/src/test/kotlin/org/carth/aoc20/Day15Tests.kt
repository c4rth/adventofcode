package org.carth.aoc20

import org.carth.common.DayTests
import org.carth.common.Puzzle.Part
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test

class Day15Tests : DayTests<Day15>(Day15::class) {
    @Test
    @Order(1)
    fun solvePartOneSample() = solve(Part.ONE, Type.TEST, expected = 436)

    @Test
    @Order(2)
    fun solvePartOne() = solve(Part.ONE, Type.INPUT, expected = 206)

    @Test
    @Order(3)
    fun solvePartTwoSample() = solve(Part.TWO, Type.TEST, expected = 175594)

    @Test
    @Order(4)
    fun solvePartTwo() = solve(Part.TWO, Type.INPUT, expected = 955)
}