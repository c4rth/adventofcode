package org.carth.aoc21

import org.carth.common.DayTests
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals


class Day18Tests : DayTests() {

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation::class)
    inner class Day18PartOneTest {

        @Test
        @Order(2)
        fun `solve part one of sample 1`() {
            // Act
            val answer = Day18(testInputAsListOfString("1")).solvePartOne()

            // Assert
            assertEquals(3488, answer)
        }

        @Test
        @Order(3)
        fun `solve part one of sample 2`() {
            // Act
            val answer = Day18(testInputAsListOfString("2")).solvePartOne()

            // Assert
            assertEquals(4140, answer)
        }

        @Test
        @Order(4)
        fun `solve part one`() {
            // Act
            val answer = Day18(inputAsListOfString()).solvePartOne()

            // Assert
            assertEquals(4111, answer)
        }
    }

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation::class)
    inner class Day18PartTwoTest {

        @Test
        @Order(1)
        fun `solve part two of sample`() {
            // Act
            val answer = Day18(testInputAsListOfString( "2")).solvePartTwo()

            // Assert
            assertEquals(3993, answer)
        }

        @Test
        @Order(2)
        fun `solve part two`() {
            // Act
            val answer = Day18(inputAsListOfString()).solvePartTwo()
            // Assert
            assertEquals(4917, answer)
        }
    }
}