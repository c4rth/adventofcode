package org.carth.aoc2023

import org.carth.common.DayTests
import org.carth.common.Puzzle.Part
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test

class Day10Tests : DayTests<Day10>(Day10::class) {

    @Test
    @Order(10)
    fun solvePartOneSample1() = solve(Part.ONE, Type.TEST, "-1", expected = 4)

    @Test
    @Order(11)
    fun solvePartOneSample2() = solve(Part.ONE, Type.TEST, "-2", expected = 8)

    @Test
    @Order(20)
    fun solvePartOne() = solve(Part.ONE, Type.INPUT, expected = 7145)

    @Test
    @Order(30)
    fun solvePartTwoSample1() = solve(Part.TWO, Type.TEST, "-1", expected = 1)

    @Test
    @Order(31)
    fun solvePartTwoSample2() = solve(Part.TWO, Type.TEST, "-2", expected = 1)

    @Test
    @Order(32)
    fun solvePartTwoSample3() = solve(Part.TWO, Type.TEST, "-3", expected = 4)

    @Test
    @Order(33)
    fun solvePartTwoSample4() = solve(Part.TWO, Type.TEST, "-4", expected = 8)

    @Test
    @Order(34)
    fun solvePartTwoSample5() = solve(Part.TWO, Type.TEST, "-5", expected = 10)

    @Test
    @Order(40)
    fun solvePartTwo() = solve(Part.TWO, Type.INPUT, expected = 445)
}