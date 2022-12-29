package org.carth.aoc22

import org.carth.common.DayTests
import org.junit.jupiter.api.*

class Day01Tests : DayTests() {

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation::class)
    inner class Day01PartOneTest {

        @Test
        @Order(1)
        fun `solve part one sample`() {

            // Act
            val answer = Day01(testInputAsText()).solvePartOne()

            // Assert
            Assertions.assertEquals(24_000, answer)
        }

        @Test
        @Order(2)
        fun `solve part one`() {
            // Act
            val answer = Day01(inputAsText()).solvePartOne()

            // Assert
            Assertions.assertEquals(67_633, answer)
        }

    }

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation::class)
    inner class Day01PartTwoTest {

        @Test
        @Order(1)
        fun `solve part two sample`() {
            // Act
            val answer = Day01(testInputAsText()).solvePartTwo()

            // Assert
            Assertions.assertEquals(45_000, answer)
        }

        @Test
        @Order(2)
        fun `solve part two`() {
            // Act
            val answer = Day01(inputAsText()).solvePartTwo()
            // Assert
            Assertions.assertEquals(199_628, answer)
        }
    }
}