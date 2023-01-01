package org.carth.aoc20

import org.carth.common.DayTests
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals


class Day03Tests : DayTests() {

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation::class)
    inner class Day03PartOneTest {

        @Test
        @Order(1)
        fun `solve part one sample`() {
            // Act
            val answer = Day03(testInputAsListOfString()).solvePartOne()

            // Assert
            assertEquals(7, answer)
        }

        @Test
        @Order(2)
        fun `solve part one`() {
            // Act
            val answer = Day03(inputAsListOfString()).solvePartOne()

            // Assert
            assertEquals(209, answer)
        }
    }

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation::class)
    inner class Day03PartTwoTest {

        @Test
        @Order(1)
        fun `solve part two sample`() {
            // Act
            val answer = Day03(testInputAsListOfString()).solvePartTwo()

            // Assert
            assertEquals(336, answer)
        }

        @Test
        @Order(2)
        fun `solve part two`() {
            // Act
            val answer = Day03(inputAsListOfString()).solvePartTwo()
            // Assert
            assertEquals(1574890240, answer)
        }
    }
}