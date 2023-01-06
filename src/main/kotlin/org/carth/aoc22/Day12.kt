package org.carth.aoc22

import org.carth.common.Point2d
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
                    val shortestPath = dijkstraShortestPath.getPath(Point2d(x, y), end)
                    if (shortestPath != null) {
                        paths.add(shortestPath.edgeList.size)
                    }
                }
            }
        }
        return paths.min()
    }

    private fun getStartEnd(): Pair<Point2d, Point2d> {
        var start: Point2d? = null
        var end: Point2d? = null
        data.forEachIndexed { y, line ->
            line.forEachIndexed { x, char ->
                if (char == 'S') {
                    start = Point2d(x, y)
                } else if (char == 'E') {
                    end = Point2d(x, y)
                }
                if (start != null && end != null) return start!! to end!!
            }
        }
        throw Error("start-end not found")
    }

    private fun Char.value(): Int = if (this == 'S') 'a'.code else if (this == 'E') 'z'.code else code

    private fun List<String>.get(point2d: Point2d): Char = this[point2d.y][point2d.x]

    private fun parseGraph(): DefaultDirectedGraph<Point2d, DefaultEdge> {
        val width = data[0].length
        val height = data.size
        val graph = DefaultDirectedGraph<Point2d, DefaultEdge>(DefaultEdge::class.java)
        val arounds = arrayOf(Point2d(1, 0), Point2d(-1, 0), Point2d(0, 1), Point2d(0, -1))
        data.forEachIndexed { y, line ->
            line.forEachIndexed { x, char ->
                val from = Point2d(x, y)
                for (around in arounds) {
                    val to = Point2d(x, y) + around
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