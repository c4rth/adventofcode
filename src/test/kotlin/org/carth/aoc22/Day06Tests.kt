package org.carth.aoc22

import org.carth.common.DayTests
import org.junit.jupiter.api.*

class Day06Tests : DayTests<Day06>(Day06::class) {

    @Test
    @Order(1)
    fun solvePartOneSample1() = solve(Part.ONE, Type.TEST, "1", expected = 7)

    @Test
    @Order(2)
    fun solvePartOneSample2() = solve(Part.ONE, Type.TEST, "2", expected = 5)

    @Test
    @Order(3)
    fun solvePartOneSample3() = solve(Part.ONE, Type.TEST, "3", expected = 6)

    @Test
    @Order(4)
    fun solvePartOneSample4() = solve(Part.ONE, Type.TEST, "4", expected = 10)

    @Test
    @Order(5)
    fun solvePartOneSample5() = solve(Part.ONE, Type.TEST, "5", expected = 11)

    @Test
    @Order(6)
    fun solvePartOne() = solve(Part.ONE, Type.INPUT, expected = 1658)

    @Test
    @Order(7)
    fun solvePartTwoSample1() = solve(Part.TWO, Type.TEST, "1", expected = 19)

    @Test
    @Order(8)
    fun solvePartTwoSample2() = solve(Part.TWO, Type.TEST, "2", expected = 23)

    @Test
    @Order(9)
    fun solvePartTwoSample3() = solve(Part.TWO, Type.TEST, "3", expected = 23)

    @Test
    @Order(10)
    fun solvePartTwoSample4() = solve(Part.TWO, Type.TEST, "4", expected = 29)

    @Test
    @Order(11)
    fun solvePartTwoSample5() = solve(Part.TWO, Type.TEST, "5", expected = 26)

    @Test
    @Order(12)
    fun solvePartTwo() = solve(Part.TWO, Type.INPUT, expected = 2260)
}