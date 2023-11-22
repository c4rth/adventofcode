package org.carth.aoc22

import org.carth.common.DayTests
import org.junit.jupiter.api.Order
import kotlin.test.Test

class Day10Tests : DayTests<Day10>(Day10::class) {

    @Test
    @Order(1)
    fun solvePartOneSample() = solve(Part.ONE, Type.TEST, expected = 13140)

    @Test
    @Order(2)
    fun solvePartOne() = solve(Part.ONE, Type.INPUT, expected = 17940)

    @Test
    @Order(3)
    fun solvePartTwoSample() {
        val expected = """
            ##..##..##..##..##..##..##..##..##..##..
            ###...###...###...###...###...###...###.
            ####....####....####....####....####....
            #####.....#####.....#####.....#####.....
            ######......######......######......####
            #######.......#######.......#######.....
        """.trimIndent()
        solve(Part.TWO, Type.TEST, expected = expected)
    }

    @Test
    @Order(4)
    fun solvePartTwo() {
        val expected = """            
            ####..##..###...##....##.####...##.####.
            ...#.#..#.#..#.#..#....#.#.......#....#.
            ..#..#....###..#..#....#.###.....#...#..
            .#...#....#..#.####....#.#.......#..#...
            #....#..#.#..#.#..#.#..#.#....#..#.#....
            ####..##..###..#..#..##..#.....##..####.
        """.trimIndent()
        solve(Part.TWO, Type.INPUT, expected = expected)
    }
}