package org.carth.aoc2023

import org.carth.common.Direction
import org.carth.common.GridBoolean
import org.carth.common.GridChar
import org.carth.common.Point2d
import org.carth.common.Puzzle

class Day16(input: String) : Puzzle<Int, Int>() {

    private val grid = GridChar(input)

    override fun solvePartOne(): Int {
        return moveBeam(Point2d(-1, 0), Direction.EAST)
    }

    override fun solvePartTwo(): Int {
        val results = mutableListOf<Int>()
        for (i in grid.widthIndices) {
            results.add(moveBeam(Point2d(i, -1), Direction.SOUTH))
            results.add(moveBeam(Point2d(i, grid.width), Direction.NORTH))
        }
        for (i in grid.heightIndices) {
            results.add(moveBeam(Point2d(-1, i), Direction.EAST))
            results.add(moveBeam(Point2d(grid.width, i), Direction.WEST))
        }
        return results.max()
    }

    private fun moveBeam(start: Point2d, startDir: Direction): Int {
        var beams = mutableSetOf<Beam>()
        val pointsSeen = mutableSetOf<Beam>()
        val energized = GridBoolean(grid.width, grid.height)
        beams.add(Beam(start, startDir))
        while (beams.size > 0) {
            val newBeams = mutableSetOf<Beam>()
            for (beam in beams) {
                val dir = beam.direction
                val newPoint = beam.point + beam.direction.toPoint2d()
                if (grid.isInside(newPoint)) {
                    energized[newPoint] = true
                    val newDirs = mutableListOf<Direction>()
                    when (grid[newPoint]) {
                        '.' -> newDirs += dir
                        '/' -> newDirs += if (dir in Direction.vertical) dir.turnLeft() else dir.turnRight()
                        '\\' -> newDirs += if (dir in Direction.horizontal) dir.turnLeft() else dir.turnRight()
                        '|' -> if (dir in Direction.vertical) newDirs += dir else newDirs += Direction.vertical
                        '-' -> if (dir in Direction.horizontal) newDirs += dir else newDirs += Direction.horizontal
                    }
                    newBeams.addAll(newDirs.map { Beam(newPoint, it) })
                }
            }
            newBeams.removeAll(pointsSeen)
            pointsSeen.addAll(newBeams)
            beams = newBeams
        }
        return energized.lines.sumOf { line -> line.map { if (it) 1 else 0 }.sum() }
    }

    data class Beam(val point: Point2d, var direction: Direction)
}