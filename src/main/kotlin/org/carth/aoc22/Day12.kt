package org.carth.aoc22

import org.carth.common.Point
import org.carth.common.Puzzle
import org.jgrapht.alg.shortestpath.DijkstraShortestPath
import org.jgrapht.graph.DefaultDirectedGraph
import org.jgrapht.graph.DefaultEdge

class Day12(input: String) : Puzzle<Int, Int>() {
    private val data = input.lines()
    override fun solvePartOne(): Int {
        val (start, end) = getStartEnd()
        val graph = parseGraph()

        val dijkstraShortestPath = DijkstraShortestPath(graph)
        val shortestPath = dijkstraShortestPath.getPath(start, end)
        return shortestPath.edgeList.size
    }

    override fun solvePartTwo(): Int {
        val (_, end) = getStartEnd()
        val graph = parseGraph()

        val dijkstraShortestPath = DijkstraShortestPath(graph)
        val paths = mutableListOf<Int>()
        data.forEachIndexed { y, line ->
            line.forEachIndexed { x, char ->
                if (char == 'a' || char == 'S') {
                    val shortestPath = dijkstraShortestPath.getPath(Point(x, y), end)
                    if (shortestPath != null) {
                        paths.add(shortestPath.edgeList.size)
                    }
                }
            }
        }
        return paths.min()
    }

    private fun getStartEnd(): Pair<Point, Point> {
        var start: Point? = null
        var end: Point? = null
        data.forEachIndexed { y, line ->
            line.forEachIndexed { x, char ->
                if (char == 'S') {
                    start = Point(x, y)
                } else if (char == 'E') {
                    end = Point(x, y)
                }
                if (start != null && end != null) return start!! to end!!
            }
        }
        throw Error("start-end not found")
    }

    private fun Char.value(): Int = if (this == 'S') 'a'.code else if (this == 'E') 'z'.code else code

    private fun List<String>.get(point: Point): Char = this[point.y][point.x]

    private fun parseGraph(): DefaultDirectedGraph<Point, DefaultEdge> {
        val width = data[0].length
        val height = data.size
        val graph = DefaultDirectedGraph<Point, DefaultEdge>(DefaultEdge::class.java)
        val arounds = arrayOf(Point(1, 0), Point(-1, 0), Point(0, 1), Point(0, -1))
        data.forEachIndexed { y, line ->
            line.forEachIndexed { x, char ->
                val from = Point(x, y)
                for (around in arounds) {
                    val to = Point(x, y) + around
                    if (to.isInside(width, height)) {
                        if (data.get(to).value() - char.value() <= 1) {
                            graph.addVertex(from)
                            graph.addVertex(to)
                            graph.addEdge(from, to)
                        }
                    }
                }
            }
        }
        return graph
    }
}