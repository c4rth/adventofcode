package org.carth.aoc22

import org.carth.common.Point
import org.carth.common.Puzzle
import kotlin.math.abs
import kotlin.math.sign

class Day09(private val data: List<String>) : Puzzle<Int, Int>() {

    override fun solvePartOne() = solve(2)

    override fun solvePartTwo() = solve(10)

    private fun parseCommands(): List<Pair<String, Int>> {
        return data.map { line ->
            line.split(" ").let {
                it[0] to it[1].toInt()
            }
        }
    }

    private fun solve(length: Int): Int {
        val commands = parseCommands()
        val snake = MutableList(length) { Point(0, 0) }
        val tailPoints = mutableListOf(Point(0, 0))
        commands.forEach { command ->
            val direction = when (command.first) {
                "U" -> Point(0, 1)
                "L" -> Point(-1, 0)
                "R" -> Point(1, 0)
                // "D"
                else -> Point(0, -1)
            }
            repeat(command.second) {
                snake[0] = snake[0] + direction
                for (index in 0 until snake.size - 1) {
                    val pointI = snake[index]
                    var pointI1 = snake[index + 1]
                    val (dx, dy) = pointI - pointI1
                    if (abs(dx) > 1 || abs(dy) > 1) {
                        pointI1 += Point(dx.sign, dy.sign)
                    }
                    snake[index + 1] = pointI1
                }
                tailPoints += snake.last()
            }
        }
        return tailPoints.distinct().count()
    }
}