package org.carth.aoc22

import org.carth.common.Puzzle
import java.util.Stack

typealias Crates = Stack<Char>

class Day05(private val data: String) : Puzzle<String, String>() {

    override fun solvePartOne(): String {
        val crates = parseCrates(data.substringBefore(System.lineSeparator() + System.lineSeparator()).lines())
        val steps = parseSteps(data.substringAfter(System.lineSeparator() + System.lineSeparator()).lines())
        steps.forEach { (times, from, to) ->
            repeat(times + 1) {
                crates[to].push(crates[from].pop())
            }
        }
        return crates.getTopCrates()
    }

    override fun solvePartTwo(): String {
        val crates = parseCrates(data.substringBefore(System.lineSeparator() + System.lineSeparator()).lines())
        val steps = parseSteps(data.substringAfter(System.lineSeparator() + System.lineSeparator()).lines())
        steps.forEach { (times, from, to) ->
            crates[to].addAll(
                ArrayList<Char>(times).apply {
                    repeat(times + 1) {
                        add(0, crates[from].pop())
                    }
                }
            )
        }
        return crates.getTopCrates()
    }

    private fun List<Crates>.getTopCrates(): String = this.map { crate -> crate.peek() }.joinToString("")

    private fun parseCrates(input: List<String>): List<Crates> {
        val indexes = input.last().filter { it.isDigit() }
        val crates = input.dropLast(1).reversed()
        return indexes.mapIndexed { index, _ ->
            Crates().apply {
                addAll(crates.mapNotNull { line -> line.getOrNull(1 + (index * 4)).takeIf { c -> c != ' ' } })
            }
        }
    }

    private fun parseSteps(input: List<String>): List<List<Int>> {
        return input.map { line ->
            line.split("move ", " to ", " from ")
                .drop(1)
                .map { it.toInt() - 1 }
        }
    }
}