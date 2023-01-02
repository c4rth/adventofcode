package org.carth.aoc22

import org.carth.common.Point
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

    private fun run(): MutableMap<Point, MutableList<Point>> {
        val moves: MutableMap<Point, MutableList<Point>> = mutableMapOf()
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

    private fun getPossibleMove(elf: Point): Point? {
        val hasNeighbors = elf.adjacents().any { it in elves }
        if (hasNeighbors)
            directions.forEach { dir ->
                val adjacentPoints = adjacents[dir]!!
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

    private fun parse(): MutableSet<Point> {
        return data.mapIndexed { row, line ->
            line.mapIndexed { col, c ->
                if (c == '#')
                    Point(col, row)
                else
                    null
            }
        }.flatten().filterNotNull().toMutableSet()
    }

    enum class Direction {
        N, S, W, E
    }

    companion object {
        private val adjacents = mutableMapOf(
            Direction.N to listOf(Point.NW, Point.N, Point.NE),
            Direction.E to listOf(Point.NE, Point.E, Point.SE),
            Direction.S to listOf(Point.SW, Point.S, Point.SE),
            Direction.W to listOf(Point.NW, Point.W, Point.SW)
        )
    }
}