package org.carth.aoc20

import org.carth.common.Point
import org.carth.common.Point3d
import org.carth.common.Point4d
import org.carth.common.Puzzle

class Day17(input: String) : Puzzle<Int, Int>() {

    private val data = input.lines()

    override fun solvePartOne(): Int {
        val grid = data.flatMapIndexed { y, line ->
            line.mapIndexed { x, c ->
                Point3d(x, y, 0) to (c == '#')
            }
        }.toMap()
        return solve(grid)
    }


    override fun solvePartTwo(): Int {
        val grid = data.flatMapIndexed { y, line ->
            line.mapIndexed { x, c ->
                Point4d(x, y, 0, 0) to (c == '#')
            }
        }.toMap()
        return solve(grid)
    }

    private fun <T : Point<T>> solve(startGrid: Map<T, Boolean>): Int {
        var grid = startGrid
        repeat(6) {
            val newGrid = grid.toMutableMap()
            grid.keys.forEach { point ->
                point.adjacent().forEach { adj ->
                    newGrid.putIfAbsent(adj, false)
                }
            }
            newGrid.entries.forEach { (point, active) ->
                val count = point.adjacent().count { adj -> grid.getOrDefault(adj, false) }
                newGrid[point] = when {
                    active && count in setOf(2, 3) -> true
                    !active && count == 3 -> true
                    else -> false
                }
            }
            grid = newGrid
        }
        return grid.count { entry -> entry.value }
    }

}
