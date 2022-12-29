package org.carth.aoc21

import org.carth.common.DayTests
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals


class Day14Tests : DayTests() {

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation::class)
    inner class Day14PartOneTest {
        @Test
        @Order(1)
        fun `solve part one of sample`() {
            // Act
            val answer = Day14(testInputAsListOfString()).solvePartOne()

            // Assert
            assertEquals(1588, answer)
        }

        @Test
        @Order(2)
        fun `solve part one`() {

            // Act
            val answer = Day14(inputAsListOfString()).solvePartOne()

            // Assert
            assertEquals(2587, answer)
        }
    }

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation::class)
    inner class Day14PartTwoTest {

        @Test
        @Order(1)
        fun `solve part two of sample`() {
            // Act
            val answer = Day14(testInputAsListOfString()).solvePartTwo()

            // Assert
            assertEquals(2188189693529, answer)
        }

        @Test
        @Order(2)
        fun `solve part two`() {
            // Act
            val answer = Day14(inputAsListOfString()).solvePartTwo()
            // Assert
            assertEquals(3318837563123, answer)
        }
    }
}