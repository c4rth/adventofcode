package org.carth.aoc22

import org.carth.common.Point
import org.carth.common.Puzzle

typealias State = Pair<Day22.Direction, Point>

class Day22(private val data: List<String>) : Puzzle<Int, Int>() {

    private val board = data.dropLast(2)
    private val perimeter = getPerimeter(board)
    private val moves = data.last().split(Regex("(?<=[RL])|(?=[RL])"))

    override fun solvePartOne(): Int {
        val adjacencyMap = mutableMapOf<State, State>()
        for ((dir, pos) in perimeter) {
            val direction = dir.turnLeft()
            var (x, y) = pos
            when (direction) {
                Direction.DOWN -> x = board[y].indexOfFirst { it != ' ' }
                Direction.RIGHT -> y = board.indexOfFirst { x in it.indices && it[x] != ' ' }
                Direction.UP -> x = board[y].indexOfLast { it != ' ' }
                Direction.LEFT -> y = board.indexOfLast { x in it.indices && it[x] != ' ' }
            }
            adjacencyMap[direction to pos] = direction to Point(x, y)
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
        var pos = Point(board[0].indexOf('.'), 0)
        var dir = Direction.DOWN
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

    private fun Point.move(direction: Direction) = when (direction) {
        Direction.DOWN -> Point(x + 1, y)
        Direction.RIGHT -> Point(x, y + 1)
        Direction.UP -> Point(x - 1, y)
        Direction.LEFT -> Point(x, y - 1)
    }

    operator fun List<String>.get(pos: Point): Char = getOrNull(pos.y)?.getOrNull(pos.x) ?: ' '

    private fun getPerimeter(board: List<String>): List<State> {
        val initialPos = Point(board[0].indexOf('.'), 0)
        return buildList {
            var pos = initialPos
            var dir = Direction.DOWN
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
            } while (pos != initialPos || dir != Direction.DOWN)
        }
    }

    enum class Direction(val value: Int) {
        DOWN(0), RIGHT(1), UP(2), LEFT(3);

        fun turnLeft() = Direction.from((this.value - 1).mod(4))

        fun turnRight() = Direction.from((this.value + 1).mod(4))

        companion object {
            infix fun from(value: Int): Direction = Direction.values().firstOrNull { it.value == value }!!
        }
    }
}