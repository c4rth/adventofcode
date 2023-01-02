package org.carth.aoc21

import org.carth.common.Puzzle
import java.lang.Integer.max
import kotlin.math.abs

class Day17(input: String) : Puzzle<Int, Int>() {

    private val data = input.lines()

    private fun readData(str: String): List<Int> =
        "target area: x=(\\d+)\\.\\.(\\d+), y=(-\\d+)\\.\\.(-\\d+)".toRegex()
            .matchEntire(str)!!
            .destructured
            .toList()
            .map { it.toInt() }

    override fun solvePartOne(): Int {
        val (fromX, toX, fromY, toY) = readData(data.first())
        val (maxHeight, _) = solve(fromX, toX, fromY, toY)
        return maxHeight
    }

    override fun solvePartTwo(): Int {
        val (fromX, toX, fromY, toY) = readData(data.first())
        val (_, number) = solve(fromX, toX, fromY, toY)
        return number
    }

    private fun solve(fromX: Int, toX: Int, fromY: Int, toY: Int): Pair<Int, Int> {
        var maxHeight = 0
        var total = 0
        for (velocityX in 1..fromX + toX) {
            for (velocityY in fromY..abs(fromY) + abs(toY)) {
                var x = 0
                var y = 0
                var vX = velocityX
                var vY = velocityY
                var higher = 0
                while (x < toX && y > fromY) {
                    x += vX
                    y += vY
                    higher = max(higher, y)
                    if (x in fromX..toX && y in fromY..toY) {
                        maxHeight = max(higher, maxHeight)
                        total += 1
                        break
                    }
                    vX = max(vX - 1, 0)
                    vY -= 1
                }
            }
        }
        return maxHeight to total
    }
}