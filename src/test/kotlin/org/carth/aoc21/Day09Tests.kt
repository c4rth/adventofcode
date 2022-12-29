package org.carth.aoc21

import org.carth.common.DayTests
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals


class Day09Tests : DayTests() {

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation::class)
    inner class Day09PartOneTest {

        @Test
        @Order(1)
        fun `solve part one of sample`() {
            // Act
            val answer = Day09(testInputAsListOfString()).solvePartOne()

            // Assert
            assertEquals(15, answer)
        }

        @Test
        @Order(2)
        fun `solve part one`() {
            // Act
            val answer = Day09(inputAsListOfString()).solvePartOne()

            // Assert
            assertEquals(496, answer)
        }
    }

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation::class)
    inner class Day09PartTwoTest {

        @Test
        @Order(1)
        fun `solve part two of sample`() {
            // Act
            val answer = Day09(testInputAsListOfString()).solvePartTwo()

            // Assert
            assertEquals(1134, answer)
        }

        @Test
        @Order(2)
        fun `solve part two`() {
            // Act
            val answer = Day09(inputAsListOfString()).solvePartTwo()
            // Assert
            assertEquals(902880, answer)
        }
    }
}