package org.carth.aoc2023

import org.carth.common.DayTests
import org.carth.common.Puzzle.Part
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test

class Day01Tests : DayTests<Day01>(Day01::class) {
    @Test
    @Order(1)
    fun solvePartOneSample() = solve(Part.ONE, Type.TEST, expected = 142)

    @Test
    @Order(2)
    fun solvePartOne() = solve(Part.ONE, Type.INPUT, expected = 55621)

    @Test
    @Order(3)
    fun solvePartTwoSample1() = solve(Part.TWO, Type.TEST, expected = 142)

    @Test
    @Order(4)
    fun solvePartTwoSample2() = solve(Part.TWO, Type.TEST, "-2", expected = 281)

    @Test
    @Order(5)
    fun solvePartTwo() = solve(Part.TWO, Type.INPUT, expected = 53592)
}