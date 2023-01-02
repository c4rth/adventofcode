package org.carth.aoc21

import org.carth.common.Puzzle

class Day02(input: String) : Puzzle<String, String>() {

    private val data = input.lines()

    private fun getCommandDelta(line: String) = line.split(" ").let { it[0] to it[1].toInt() }

    override fun solvePartOne(): String {
        var horizontal = 0
        var depth = 0
        data.forEach {
            val (command, delta) = getCommandDelta(it)
            when (command) {
                "forward" -> horizontal += delta
                "down" -> depth += delta
                "up" -> depth -= delta
            }
        }
        return (horizontal * depth).toString()
    }

    override fun solvePartTwo(): String {
        var horizontal = 0
        var depth = 0
        var aim = 0
        data.forEach {
            val (command, delta) = getCommandDelta(it)
            when (command) {
                "forward" -> {
                    horizontal += delta
                    depth += aim * delta
                }

                "down" -> aim += delta
                "up" -> aim -= delta
            }
        }
        return (horizontal * depth).toString()
    }
}