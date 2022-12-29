package org.carth.aoc21

import org.carth.common.DayTests
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals


class Day08Tests : DayTests() {

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation::class)
    inner class Day08PartOneTest {

        @Test
        @Order(1)
        fun `solve part one of sample`() {
            // Act
            val answer = Day08(testInputAsListOfString()).solvePartOne()

            // Assert
            assertEquals(26, answer)
        }

        @Test
        @Order(2)
        fun `solve part one`() {
            // Act
            val answer = Day08(inputAsListOfString()).solvePartOne()

            // Assert
            assertEquals(330, answer)
        }
    }

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation::class)
    inner class Day08PartTwoTest {

        @Test
        @Order(1)
        fun `solve part two of sample`() {
            // Act
            val answer = Day08(testInputAsListOfString()).solvePartTwo()

            // Assert
            assertEquals(61229, answer)
        }

        @Test
        @Order(2)
        fun `solve part two`() {
            // Act
            val answer = Day08(inputAsListOfString()).solvePartTwo()
            // Assert
            assertEquals(1010472, answer)
        }
    }
}