package org.carth.aoc22

import mu.KotlinLogging
import org.carth.common.DayTests
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals

class Day10Tests : DayTests() {

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation::class)
    inner class Day10PartOneTest {

        @Test
        @Order(1)
        fun `solve part one of sample`() {
            // Act
            val answer = Day10(testInputAsListOfString()).solvePartOne()

            // Assert
            assertEquals(13_140, answer)
        }

        @Test
        @Order(2)
        fun `solve part one`() {
            // Act
            val answer = Day10(inputAsListOfString()).solvePartOne()

            // Assert
            assertEquals(17_940, answer)
        }
    }

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation::class)
    inner class Day10PartTwoTest {

        private val logger = KotlinLogging.logger {}

        @Test
        @Order(1)
        fun `solve part two of sample`() {
            // Act
            val answer = Day10(testInputAsListOfString()).solvePartTwo()

            val expected = "##..##..##..##..##..##..##..##..##..##..\n" +
                    "###...###...###...###...###...###...###.\n" +
                    "####....####....####....####....####....\n" +
                    "#####.....#####.....#####.....#####.....\n" +
                    "######......######......######......####\n" +
                    "#######.......#######.......#######....."
            // Assert
            assertEquals(expected, answer)
        }

        @Test
        @Order(2)
        fun `solve part two`() {
            // Act
            val answer = Day10(inputAsListOfString()).solvePartTwo()
            logger.info { "\n" + answer }
            // ZCBAJFJZ
        }
    }
}