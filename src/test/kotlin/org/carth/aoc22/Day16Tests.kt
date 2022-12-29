package org.carth.aoc22

import org.carth.common.DayTests
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals

class Day16Tests : DayTests() {

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation::class)
    inner class Day16PartOneTest {

        @Test
        @Order(1)
        fun `solve part one of sample`() {
            // Act
            val answer = Day16(testInputAsListOfString()).solvePartOne()

            // Assert
            assertEquals(1651, answer)
        }

        @Test
        @Order(2)
        fun `solve part one`() {
            // Act
            val answer = Day16(inputAsListOfString()).solvePartOne()

            // Assert
            assertEquals(2114, answer)
        }
    }

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation::class)
    inner class Day16PartTwoTest {

        @Test
        @Order(1)
        fun `solve part two of sample`() {
            // Act
            val answer = Day16(testInputAsListOfString()).solvePartTwo()

            // Assert
            assertEquals(1707, answer)
        }

        @Test
        @Order(2)
        fun `solve part two`() {
            // Act
            val answer = Day16(inputAsListOfString()).solvePartTwo()
            // Assert
            assertEquals(2666, answer)
        }
    }

}