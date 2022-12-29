package org.carth.aoc22

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
            val answer = Day09(testInputAsListOfString( "1")).solvePartOne()

            // Assert
            assertEquals(13, answer)
        }

        @Test
        @Order(2)
        fun `solve part one`() {
            // Act
            val answer = Day09(inputAsListOfString()).solvePartOne()

            // Assert
            assertEquals(6026, answer)
        }
    }

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation::class)
    inner class Day09PartTwoTest {

        @Test
        @Order(1)
        fun `solve part two of sample 1`() {
            // Act
            val answer = Day09(testInputAsListOfString( "1")).solvePartTwo()

            // Assert
            assertEquals(1, answer)
        }

        @Test
        @Order(2)
        fun `solve part two of sample 2`() {
            // Act
            val answer = Day09(testInputAsListOfString( "2")).solvePartTwo()

            // Assert
            assertEquals(36, answer)
        }

        @Test
        @Order(3)
        fun `solve part two`() {
            // Act
            val answer = Day09(inputAsListOfString()).solvePartTwo()
            // Assert
            assertEquals(2273, answer)
        }
    }
}