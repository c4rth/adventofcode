package org.carth.aoc21

import org.carth.common.DayTests
import org.carth.common.Puzzle.Part
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test


class Day18Tests : DayTests<Day18>(Day18::class) {
    @Test
    @Order(1)
    fun solvePartOneSample1() = solve(Part.ONE, Type.TEST, "1", expected = 3488)

    @Test
    @Order(1)
    fun solvePartOneSample2() = solve(Part.ONE, Type.TEST, "2", expected = 4140)

    @Test
    @Order(2)
    fun solvePartOne() = solve(Part.ONE, Type.INPUT, expected = 4111)

    @Test
    @Order(3)
    fun solvePartTwoSample1() = solve(Part.TWO, Type.TEST, "2", expected = 3993)

    @Test
    @Order(4)
    fun solvePartTwo() = solve(Part.TWO, Type.INPUT, expected = 4917)
}