package org.carth.aoc22

import org.carth.common.DayTests
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals

class Day15Tests : DayTests() {

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation::class)
    inner class Day15PartOneTest {

        @Test
        @Order(1)
        fun `solve part one of sample`() {
            // Act
            val answer = Day15(testInputAsListOfString()).solvePartOne(10)

            // Assert
            assertEquals(26, answer)
        }

        @Test
        @Order(2)
        fun `solve part one`() {
            // Act
            val answer = Day15(inputAsListOfString()).solvePartOne(2_000_000)

            // Assert
            assertEquals(4876693, answer)
        }
    }

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation::class)
    inner class Day15PartTwoTest {

        @Test
        @Order(1)
        fun `solve part two of sample`() {
            // Act
            val answer = Day15(testInputAsListOfString()).solvePartTwo(40)

            // Assert
            assertEquals(56_000_011L, answer)
        }

        @Test
        @Order(2)
        fun `solve part two`() {
            // Act
            val answer = Day15(inputAsListOfString()).solvePartTwo(4_000_000)
            // Assert
            assertEquals(11_645_454_855_041L, answer)
        }
    }

}