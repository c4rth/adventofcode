package org.carth.aoc22

import org.carth.common.Puzzle
import kotlin.math.max


class Day16(data: List<String>) : Puzzle<Int, Int>() {

    private val map: Map<String, Cave> =
        data.map {
            it.split("Valve ", " has flow rate=", "; tunnel").drop(1)
        }.associate { line ->
            val list = if (line[2].startsWith("s")) line[2].drop(17) else line[2].drop(16)
            line[0] to Cave(line[0], line[1].toInt(), list.split(", ").map { Tunnel(1, it.trim()) })
        }
    private val reducedMap: Map<String, Cave> =
        map.filter { (_, cave) ->
            cave.flow > 0
        }

    init {
        map["AA"]!!.reduceTunnel(map, reducedMap.size)
        reducedMap.forEach { (_, cave) -> cave.reduceTunnel(map, reducedMap.size - 1) }
    }

    override fun solvePartOne(): Int {
        return bestPath(30, false).pressure
    }

    override fun solvePartTwo(): Int {
        val reducedList = reducedMap.map { it.value }
        val elephantMap = mutableMapOf<String, Boolean>()
        var bestTotal = 0
        for (db in 0 until (1 shl reducedList.size)) {
            if (db.countOneBits() != reducedMap.size / 2) continue

            reducedList.forEachIndexed { index, cave ->
                elephantMap[cave.name] = ((db and (1 shl index)) != 0)
            }
            reducedList.forEach { cave ->
                cave.reducedTunnel.forEach {tunnel ->
                    tunnel.elephant = elephantMap[tunnel.caveName]!!
                }
            }
            map["AA"]!!.reducedTunnel.forEach { tunnel ->
                tunnel.elephant = elephantMap[tunnel.caveName]!!
            }
            val bestHumanPath = bestPath(26, false)
            val bestElephantPath = bestPath(26, true)
            bestTotal = max(bestTotal, bestHumanPath.pressure + bestElephantPath.pressure)
        }
        return bestTotal
    }

    private fun bestPath(time: Int, elephant: Boolean): Path {
        val paths = ArrayDeque(listOf(Path("AA", 0, 0, mutableMapOf("AA" to true))))
        var bestPath = paths.first()
        while (paths.size > 0) {
            val path = paths.removeFirst()
            val curCave = map[path.caveName]!!
            for (tunnel in curCave.reducedTunnel) {
                if (tunnel.elephant == elephant) {
                    val openTime = path.time + tunnel.distance + 1
                    if (openTime < time && !path.opened.getOrDefault(tunnel.caveName, false)) {
                        val newVisited = path.opened.toMutableMap()
                        newVisited[tunnel.caveName] = true
                        val newPressure = path.pressure + (time - openTime) * map[tunnel.caveName]!!.flow
                        paths.add(Path(tunnel.caveName, newPressure, openTime, newVisited))
                    }
                }
            }
            if (path.pressure > bestPath.pressure) bestPath = path
        }
        return bestPath
    }

    data class Tunnel(val distance: Int, val caveName: String, var elephant: Boolean = false)

    data class Cave(
        val name: String,
        val flow: Int,
        val tunnel: List<Tunnel>,
        val reducedTunnel: MutableList<Tunnel> = mutableListOf()
    ) {
        fun reduceTunnel(map: Map<String, Cave>, limit: Int) {
            var queue = listOf(this)
            var distance = 0
            val found = mutableMapOf(name to true)
            while (true) {
                val newQueue = mutableListOf<Cave>()
                distance += 1
                for (cave in queue) {
                    for (tunnel in cave.tunnel) {
                        val nextCave = map[tunnel.caveName]!!
                        if (nextCave.flow > 0 && !found.getOrDefault(nextCave.name, false)) {
                            this.reducedTunnel.add(Tunnel(distance, nextCave.name))
                            found[nextCave.name] = true
                            if (this.reducedTunnel.size == limit) {
                                return
                            }
                        }
                        newQueue.add(nextCave)
                    }
                }
                queue = newQueue
            }
        }
    }

    data class Path(
        val caveName: String,
        val pressure: Int,
        val time: Int,
        val opened: MutableMap<String, Boolean>
    )

}