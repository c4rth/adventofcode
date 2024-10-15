package org.carth.aoc22

import org.carth.common.DayTests
import org.carth.common.Puzzle.Part
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test


class Day13Tests : DayTests<Day13>(Day13::class) {

    @Test
    @Order(1)
    fun solvePartOneSample() = solve(Part.ONE, Type.TEST, expected = 13)

    @Test
    @Order(2)
    fun solvePartOne() = solve(Part.ONE, Type.INPUT, expected = 6076)

    @Test
    @Order(3)
    fun solvePartTwoSample() = solve(Part.TWO, Type.TEST, expected = 140)

    @Test
    @Order(4)
    fun solvePartTwo() = solve(Part.TWO, Type.INPUT, expected = 24805)
}