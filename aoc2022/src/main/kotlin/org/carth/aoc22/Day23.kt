package org.carth.aoc22

import org.carth.common.Point2d
import org.carth.common.Puzzle


class Day23(input: String) : Puzzle<Int, Int>() {

    private val data = input.lines()
    private val elves = parse()

    private var directions = mutableListOf(Direction.N, Direction.S, Direction.W, Direction.E)

    override fun solvePartOne(): Int {
        repeat(10) {
            run()
        }
        return compArea()
    }

    override fun solvePartTwo(): Int {
        var count = 0
        do {
            val moves = run()
            count++
        } while (moves.isNotEmpty())
        return count
    }

    private fun run(): MutableMap<Point2d, MutableList<Point2d>> {
        val moves: MutableMap<Point2d, MutableList<Point2d>> = mutableMapOf()
        // possible moves
        elves.forEach { elf ->
            getPossibleMove(elf)?.let { next ->
                moves.getOrPut(next) { mutableListOf() }.add(elf)
            }
        }
        // move
        moves.filter { (_, list) -> list.size == 1 }
            .forEach { (next, list) ->
                elves.remove(list[0])
                elves.add(next)
            }
        // rotate directions
        directions.add(directions.removeAt(0))
        return moves
    }

    private fun getPossibleMove(elf: Point2d): Point2d? {
        val hasNeighbors = elf.adjacent().any { it in elves }
        if (hasNeighbors)
            directions.forEach { dir ->
                val adjacentPoints = adjacent[dir]!!
                val noNeighborFound = adjacentPoints
                    .map { elf + it }
                    .none { it in elves }
                if (noNeighborFound) {
                    return elf + adjacentPoints[1]
                }
            }
        return null
    }

    private fun compArea(): Int {
        var min = elves.first()
        var max = min
        elves.forEach { elf ->
            min = min.min(elf)
            max = max.max(elf)
        }
        return (max.x - min.x + 1) * (max.y - min.y + 1) - elves.size
    }

    private fun parse(): MutableSet<Point2d> {
        return data.mapIndexed { row, line ->
            line.mapIndexed { col, c ->
                if (c == '#')
                    Point2d(col, row)
                else
                    null
            }
        }.flatten().filterNotNull().toMutableSet()
    }

    enum class Direction {
        N, S, W, E
    }

    companion object {
        private val adjacent = mutableMapOf(
            Direction.N to listOf(Point2d.NW, Point2d.N, Point2d.NE),
            Direction.E to listOf(Point2d.NE, Point2d.E, Point2d.SE),
            Direction.S to listOf(Point2d.SW, Point2d.S, Point2d.SE),
            Direction.W to listOf(Point2d.NW, Point2d.W, Point2d.SW)
        )
    }
}