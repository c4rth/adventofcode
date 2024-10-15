package org.carth.aoc2023

import org.carth.common.DayTests
import org.carth.common.Puzzle.Part
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test

class Day14Tests : DayTests<Day14>(Day14::class) {
    @Test
    @Order(10)
    fun solvePartOneSample() = solve(Part.ONE, Type.TEST, expected = 136)

    @Test
    @Order(20)
    fun solvePartOne() = solve(Part.ONE, Type.INPUT, expected = 109466)

    @Test
    @Order(30)
    fun solvePartTwoSample() = solve(Part.TWO, Type.TEST, expected = 64)

    @Test
    @Order(40)
    fun solvePartTwo() = solve(Part.TWO, Type.INPUT, expected = 94585)
}