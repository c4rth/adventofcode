package org.carth.aoc2023

import org.carth.common.DayTests
import org.carth.common.Puzzle.Part
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test

class Day21Tests : DayTests<Day21>(Day21::class) {
    @Test
    @Order(10)
    fun solvePartOneSample() = solve(Part.ONE, Type.TEST, expected = 42L)

    @Test
    @Order(20)
    fun solvePartOne() = solve(Part.ONE, Type.INPUT, expected = 3858L)

    @Test
    @Order(30)
    fun solvePartTwoSample1() = solve(Part.TWO, Type.TEST, args = listOf(6), expected = 16L)

    @Test
    @Order(31)
    fun solvePartTwoSample2() = solve(Part.TWO, Type.TEST, args = listOf(10), expected = 50L)

    @Test
    @Order(32)
    fun solvePartTwoSample3() = solve(Part.TWO, Type.TEST, args = listOf(50), expected = 1594L)

    @Test
    @Order(33)
    fun solvePartTwoSample4() = solve(Part.TWO, Type.TEST, args = listOf(100), expected = 6536L)

    @Test
    @Order(34)
    fun solvePartTwoSample5() = solve(Part.TWO, Type.TEST, args = listOf(500), expected = 167004L)

    @Test
    @Order(35)
    fun solvePartTwoSample6() = solve(Part.TWO, Type.TEST, args = listOf(1000), expected = 668697L)

    @Test
    @Order(36)
    fun solvePartTwoSample7() = solve(Part.TWO, Type.TEST, args = listOf(5000), expected = 16733044L)

    @Test
    @Order(37)
    fun solvePartTwoSample8() = solve(Part.TWO, Type.TEST, args = listOf(26501365), expected = 470149643712804L)

    @Test
    @Order(40)
    fun solvePartTwo() = solve(Part.TWO, Type.INPUT, args = listOf(26501365), expected = 636350496972143L)
}