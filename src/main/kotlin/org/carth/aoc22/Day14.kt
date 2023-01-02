package org.carth.aoc22

import org.carth.common.Point
import org.carth.common.Puzzle
import java.lang.Integer.max
import java.lang.Integer.min

class Day14(input: String) : Puzzle<Int, Int>() {
    private val data = input.lines()
    private val start = Point(500, 0)
    private val down = Point(0, 1)
    private val downLeft = Point(-1, 1)
    private val downRight = Point(1, 1)

    override fun solvePartOne(): Int {
        val grid = parse()
        val bottom: Int = grid.maxOf { point -> point.y }
        return solve(grid) { sand -> sand.y < bottom }
    }

    override fun solvePartTwo(): Int {
        val grid = parse()
        val left: Int = grid.minOf { point -> point.x }
        val right: Int = grid.maxOf { point -> point.x }
        val bottom: Int = grid.maxOf { point -> point.y } + 2
        for (x in left / 2..right * 2) {
            grid.add(Point(x, bottom))
        }
        return solve(grid) { _ -> !grid.contains(start) }
    }

    private fun solve(grid: MutableCollection<Point>, test: (Point) -> Boolean): Int {
        var sand = start
        var count = 0
        do {
            val newSand = moveSand(grid, sand)
            sand = if (newSand == sand) {
                count++
                grid.add(sand)
                start
            } else {
                newSand
            }
        } while (test(sand))
        return count
    }

    private fun moveSand(grid: MutableCollection<Point>, sand: Point): Point {
        return if (!grid.contains(sand + down))
            sand + down
        else if (!grid.contains(sand + downLeft))
            sand + downLeft
        else if (!grid.contains(sand + downRight))
            sand + downRight
        else
            sand
    }

    private fun parse(): MutableCollection<Point> {
        val grid = mutableSetOf<Point>()
        data.forEach { line ->
            line.split(" -> ")
                .windowed(2).forEach { (from, to) ->
                    val (fromX, fromY) = from.split(",").map { it.toInt() }
                    val (toX, toY) = to.split(",").map { it.toInt() }
                    if (fromX == toX) {
                        for (y in min(fromY, toY)..max(fromY, toY)) grid.add(Point(fromX, y))
                    } else if (fromY == toY) {
                        for (x in min(fromX, toX)..max(fromX, toX)) grid.add(Point(x, fromY))
                    }
                }
        }
        return grid
    }

}