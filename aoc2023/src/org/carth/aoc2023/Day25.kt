package org.carth.aoc2023

import org.carth.common.Puzzle
import org.jgrapht.alg.StoerWagnerMinimumCut
import org.jgrapht.graph.DefaultWeightedEdge
import org.jgrapht.graph.SimpleWeightedGraph

class Day25(private val input: String) : Puzzle<Int, Int>() {

    override fun solvePartOne(): Int {
        val graph = SimpleWeightedGraph<String, DefaultWeightedEdge>(DefaultWeightedEdge::class.java)

        input.lines().forEach { line ->
            val (from, tos) = line.split(": ")
            graph.addVertex(from)
            tos.split(" ").forEach { to ->
                graph.addVertex(to)
                graph.addEdge(from, to)
            }
        }

        val discGroup = StoerWagnerMinimumCut(graph).minCut()

        return (graph.vertexSet().size - discGroup.size) * discGroup.size
    }

    override fun solvePartTwo(): Int {
        return 0
    }
}