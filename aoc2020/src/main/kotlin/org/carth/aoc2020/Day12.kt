package org.carth.aoc2020

import org.carth.common.Direction
import org.carth.common.Point2d
import org.carth.common.Puzzle

class Day12(input: String) : Puzzle<Int, Int>() {

    val data = input.lines().map { line ->
        line[0] to line.drop(1).toInt()
    }

    override fun solvePartOne(): Int {
        var dir = Direction.EAST
        var pos = Point2d(0, 0)
        data.forEach { instr ->
            when (instr.first) {
                'N' -> pos += Point2d(0, instr.second)
                'S' -> pos -= Point2d(0, instr.second)
                'W' -> pos -= Point2d(instr.second, 0)
                'E' -> pos += Point2d(instr.second, 0)
                'L' -> repeat(instr.second / 90) { dir = dir.turnRight() } // inversed
                'R' -> repeat(instr.second / 90) { dir = dir.turnLeft() } // inversed
                'F' -> {
                    when (dir) {
                        Direction.NORTH -> pos += Point2d(0, instr.second)
                        Direction.SOUTH -> pos -= Point2d(0, instr.second)
                        Direction.WEST -> pos -= Point2d(instr.second, 0)
                        Direction.EAST -> pos += Point2d(instr.second, 0)
                    }
                }
            }
        }
        return pos.manhattan(Point2d.ZERO)
    }

    override fun solvePartTwo(): Int {
        var waypoint = Point2d(10, 1)
        var pos = Point2d(0, 0)
        data.forEach { instr ->
            when (instr.first) {
                'N' -> waypoint += Point2d(0, instr.second)
                'S' -> waypoint -= Point2d(0, instr.second)
                'W' -> waypoint -= Point2d(instr.second, 0)
                'E' -> waypoint += Point2d(instr.second, 0)
                'L' -> repeat(instr.second / 90) { waypoint = Point2d(-1 * waypoint.y, waypoint.x) }
                'R' -> repeat(instr.second / 90) { waypoint = Point2d(waypoint.y, -1 * waypoint.x) }
                'F' -> repeat(instr.second) { pos += waypoint }
            }
        }
        return pos.manhattan(Point2d.ZERO)
    }

}
