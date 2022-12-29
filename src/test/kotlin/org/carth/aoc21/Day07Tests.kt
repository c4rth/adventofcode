package org.carth.aoc21

import org.carth.common.DayTests
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals


class Day07Tests : DayTests() {

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation::class)
    inner class Day07PartOneTest {

        @Test
        @Order(1)
        fun `solve part one of sample`() {
            // Act
            val answer = Day07(testInputAsListOfString()).solvePartOne()

            // Assert
            assertEquals(37, answer)
        }

        @Test
        @Order(2)
        fun `solve part one`() {
            // Act
            val answer = Day07(inputAsListOfString()).solvePartOne()

            // Assert
            assertEquals(352707, answer)
        }
    }

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation::class)
    inner class Day07PartTwoTest {

        @Test
        @Order(1)
        fun `solve part two of sample`() {
            // Act
            val answer = Day07(testInputAsListOfString()).solvePartTwo()

            // Assert
            assertEquals(168, answer)
        }

        @Test
        @Order(2)
        fun `solve part two`() {
            // Act
            val answer = Day07(inputAsListOfString()).solvePartTwo()
            // Assert
            assertEquals(95519693, answer)
        }
    }
}