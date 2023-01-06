package org.carth.aoc22

import org.carth.common.Direction
import org.carth.common.Point2d
import org.carth.common.Puzzle

typealias State = Pair<Direction, Point2d>

class Day22(input: String) : Puzzle<Int, Int>() {
    private val data = input.lines()
    private val board = data.dropLast(2)
    private val perimeter = getPerimeter(board)
    private val moves = data.last().split(Regex("(?<=[RL])|(?=[RL])"))

    override fun solvePartOne(): Int {
        val adjacencyMap = mutableMapOf<State, State>()
        for ((dir, pos) in perimeter) {
            val direction = dir.turnLeft()
            var (x, y) = pos
            when (direction) {
                Direction.SOUTH -> x = board[y].indexOfFirst { it != ' ' }
                Direction.EAST -> y = board.indexOfFirst { x in it.indices && it[x] != ' ' }
                Direction.NORTH -> x = board[y].indexOfLast { it != ' ' }
                Direction.WEST -> y = board.indexOfLast { x in it.indices && it[x] != ' ' }
            }
            adjacencyMap[direction to pos] = direction to Point2d(x, y)
        }
        return solve(adjacencyMap)
    }

    override fun solvePartTwo(): Int {
        val sideLength = data[0].length / 3
        val adjacencyMap = mutableMapOf<State, State>()
        val edges = perimeter.chunked(sideLength).map { it[0].first to it }.toMutableList()
        while (edges.isNotEmpty()) {
            var i = 0
            while (i < edges.lastIndex) {
                val a = edges[i]
                val b = edges[i + 1]
                if ((a.first.value - b.first.value).mod(4) == 1) {
                    edges.subList(i, i + 2).clear()
                    for (j in i..edges.lastIndex) {
                        val edge = edges[j]
                        edges[j] = edge.first.turnLeft() to edge.second
                    }
                    for (j in 0 until sideLength) {
                        val (dir1, pos1) = a.second[j]
                        val (dir2, pos2) = b.second[sideLength - j - 1]
                        adjacencyMap[dir1.turnLeft() to pos1] = dir2.turnRight() to pos2
                        adjacencyMap[dir2.turnLeft() to pos2] = dir1.turnRight() to pos1
                    }
                } else {
                    i++
                }
            }
        }
        return solve(adjacencyMap)
    }

    fun solve(adjacencyMap: Map<State, State>): Int {
        var pos = Point2d(board[0].indexOf('.'), 0)
        var dir = Direction.SOUTH
        for (move in moves) {
            when (move) {
                "L" -> dir = dir.turnLeft()
                "R" -> dir = dir.turnRight()
                else -> repeat(move.toInt()) {
                    val (newDir, newPos) = adjacencyMap[dir to pos] ?: (dir to pos.move(dir))
                    if (board[newPos] == '#') return@repeat
                    dir = newDir
                    pos = newPos
                }
            }
        }
        return 1000 * (pos.y + 1) + 4 * (pos.x + 1) + dir.value
    }

    private fun Point2d.move(direction: Direction) = when (direction) {
        Direction.SOUTH -> Point2d(x + 1, y)
        Direction.EAST -> Point2d(x, y + 1)
        Direction.NORTH -> Point2d(x - 1, y)
        Direction.WEST -> Point2d(x, y - 1)
    }

    operator fun List<String>.get(pos: Point2d): Char = getOrNull(pos.y)?.getOrNull(pos.x) ?: ' '

    private fun getPerimeter(board: List<String>): List<State> {
        val initialPos = Point2d(board[0].indexOf('.'), 0)
        return buildList {
            var pos = initialPos
            var dir = Direction.SOUTH
            do {
                add(State(dir, pos))
                val forward = pos.move(dir)
                if (board[forward] == ' ') {
                    dir = dir.turnRight()
                } else {
                    val left = forward.move(dir.turnLeft())
                    if (board[left] == ' ') {
                        pos = forward
                    } else {
                        pos = left
                        dir = dir.turnLeft()
                    }
                }
            } while (pos != initialPos || dir != Direction.SOUTH)
        }
    }

}