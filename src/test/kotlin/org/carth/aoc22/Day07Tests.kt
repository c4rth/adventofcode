package org.carth.aoc22

import org.carth.common.DayTests
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals

class Day07Tests : DayTests() {

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation::class)
    inner class Day06PartOneTest {

        @Test
        @Order(1)
        fun `solve part one of sample`() {
            // Act
            val answer = Day07(testInputAsListOfString()).solvePartOne()

            // Assert
            assertEquals(95_437, answer)
        }

        @Test
        @Order(2)
        fun `solve part one`() {
            // Act
            val answer = Day07(inputAsListOfString()).solvePartOne()

            // Assert
            assertEquals(1_642_503, answer)
        }
    }

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation::class)
    inner class Day06PartTwoTest {

        @Test
        @Order(1)
        fun `solve part two of sample`() {
            // Act
            val answer = Day07(testInputAsListOfString()).solvePartTwo()

            // Assert
            assertEquals(24_933_642, answer)
        }

        @Test
        @Order(2)
        fun `solve part two`() {
            // Act
            val answer = Day07(inputAsListOfString()).solvePartTwo()
            // Assert
            assertEquals(6_999_588, answer)
        }
    }
}