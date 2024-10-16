package org.carth.aoc2024

import org.carth.common.Puzzle2

fun main() = Day01().solve()

class Day01() : Puzzle2() {

    private val numbers = listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")

    @Sample(expected = "142")
    @Puzzle(expected = "55621")
    override fun solvePart1() = getIntValues().sum().toString()

    @Sample(expected = "142")
    @Sample(suffix="2", expected = "281")
    @Puzzle(expected = "53592")
    override fun solvePart2() = getStringValues().sum().toString()

    private fun getIntValues(): List<Int> {
        return input.split(System.lineSeparator())
            .map { line -> line[line.indexOfFirst { c -> c.isDigit() }].toString() + line[line.indexOfLast { c -> c.isDigit() }] }
            .map { it.toInt() }
    }

    private fun getStringValues(): List<Int> {
        return input.split(System.lineSeparator())
            .map { getFirstNumber(it) + getLastNumber(it) }
            .map { it.toInt() }
    }

    private fun mapNumber(number: String): String = (numbers.indexOfFirst { it.startsWith(number) } + 1).toString()

    private fun getFirstNumber(line: String): String {
        val idx = numbers.map { n -> line.indexOf(n) }.filter { i -> i > -1 }.minOrNull() ?: Int.MAX_VALUE
        val idxN = line.indexOfFirst { c -> c.isDigit() }
        if (idxN > -1 && idxN < idx) {
            return line[idxN].toString()
        }
        return mapNumber(line.substring(idx, idx + 2))
    }

    private fun getLastNumber(line: String): String {
        val idx = numbers.map { n -> line.lastIndexOf(n) }.filter { i -> i > -1 }.maxOrNull() ?: Int.MIN_VALUE
        val idxN = line.indexOfLast { c -> c.isDigit() }
        if (idxN > -1 && idxN > idx) {
            return line[idxN].toString()
        }
        return mapNumber(line.substring(idx, idx + 2))
    }
}