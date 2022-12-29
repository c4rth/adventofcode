package org.carth.aoc21

import org.carth.common.DayTests
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals


class Day05Tests : DayTests() {

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation::class)
    inner class Day05PartOneTest {

        @Test
        @Order(1)
        fun `solve part one of sample`() {
            // Act
            val answer = Day05(testInputAsListOfString()).solvePartOne()

            // Assert
            assertEquals( 5, answer)
        }

        @Test
        @Order(2)
        fun `solve part one`() {
            // Act
            val answer = Day05(inputAsListOfString()).solvePartOne()

            // Assert
            assertEquals( 7473, answer)
        }
    }

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation::class)
    inner class Day05PartTwoTest {

        @Test
        @Order(1)
        fun `solve part two of sample`() {
            // Act
            val answer = Day05(testInputAsListOfString()).solvePartTwo()

            // Assert
            assertEquals( 12, answer)
        }

        @Test
        @Order(2)
        fun `solve part two`() {
            // Act
            val answer = Day05(inputAsListOfString()).solvePartTwo()
            // Assert
            assertEquals( 24164, answer)
        }
    }
}