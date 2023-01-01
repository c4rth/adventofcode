package org.carth.aoc20

import org.carth.common.DayTests
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals


class Day01Tests : DayTests() {

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation::class)
    inner class Day01PartOneTest {

        @Test
        @Order(1)
        fun `solve part one sample`() {

            // Act
            val answer = Day01(testInputAsListOfInt()).solvePartOne()

            // Assert
            assertEquals(514579, answer)
        }

        @Test
        @Order(2)
        fun `solve part one`() {
            // Act
            val answer = Day01(inputAsListOfInt()).solvePartOne()

            // Assert
            assertEquals(1020099, answer)
        }
    }

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation::class)
    inner class Day01PartTwoTest {

        @Test
        @Order(1)
        fun `solve part two sample`() {
            // Act
            val answer = Day01(testInputAsListOfInt()).solvePartTwo()

            // Assert
            assertEquals(241861950, answer)
        }

        @Test
        @Order(2)
        fun `solve part two`() {
            // Act
            val answer = Day01(inputAsListOfInt()).solvePartTwo()
            // Assert
            assertEquals(49214880, answer)
        }
    }
}