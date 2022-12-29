package org.carth.aoc21

import org.carth.common.DayTests
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals


class Day19Tests : DayTests() {

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation::class)
    inner class Day19PartOneTest {

        @Test
        @Order(1)
        fun `solve part one of sample`() {
            // Act
            val answer = Day19(testInputAsListOfString()).solvePartOne()

            // Assert
            assertEquals(78, answer)
        }

        @Test
        @Order(2)
        fun `solve part one`() {
            // Act
            val answer = Day19(inputAsListOfString()).solvePartOne()

            // Assert
            assertEquals(479, answer)
        }
    }

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation::class)
    inner class Day19PartTwoTest {

        @Test
        @Order(1)
        fun `solve part two of sample`() {
            // Act
            val answer = Day19(testInputAsListOfString()).solvePartTwo()

            // Assert
            assertEquals(3621, answer)
        }

        @Test
        @Order(2)
        fun `solve part two`() {
            // Act
            val answer = Day19(inputAsListOfString()).solvePartTwo()
            // Assert
            assertEquals(13113, answer)
        }
    }
}