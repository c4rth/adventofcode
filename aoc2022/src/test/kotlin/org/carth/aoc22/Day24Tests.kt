package org.carth.aoc22

import org.carth.common.DayTests
import org.carth.common.Puzzle.Part
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test

class Day24Tests : DayTests<Day24>(Day24::class) {

    @Test
    @Order(1)
    fun solvePartOneSample() = solve(Part.ONE, Type.TEST, expected = 18)

    @Test
    @Order(2)
    fun solvePartOne() = solve(Part.ONE, Type.INPUT, expected = 242)

    @Test
    @Order(3)
    fun solvePartTwoSample() = solve(Part.TWO, Type.TEST, expected = 54)

    @Test
    @Order(4)
    fun solvePartTwo() = solve(Part.TWO, Type.INPUT, expected = 720)
}