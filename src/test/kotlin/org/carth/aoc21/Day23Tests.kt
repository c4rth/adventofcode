package org.carth.aoc21

import org.carth.common.DayTests
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals


class Day23Tests : DayTests() {

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation::class)
    inner class Day23PartOneTest {

        @Test
        @Order(1)
        fun `solve part one of sample`() {
            // Act
            val answer = Day23(testInputAsListOfString("1")).solvePartOne()

            // Assert
            assertEquals(12521, answer)
        }

        @Test
        @Order(2)
        fun `solve part one`() {
            // Act
            val answer = Day23(inputAsListOfString("1")).solvePartOne()

            // Assert
            assertEquals(11536, answer)
        }
    }

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation::class)
    inner class Day23PartTwoTest {

        @Test
        @Order(1)
        fun `solve part two of sample`() {
            // Act
            val answer = Day23(testInputAsListOfString("2")).solvePartTwo()

            // Assert
            assertEquals(44169, answer)
        }

        @Test
        @Order(2)
        fun `solve part two`() {
            // Act
            val answer = Day23(inputAsListOfString("2")).solvePartTwo()
            // Assert
            assertEquals(55136, answer)
        }
    }
}