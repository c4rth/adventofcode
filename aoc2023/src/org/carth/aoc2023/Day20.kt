package org.carth.aoc2023

import org.carth.common.Puzzle
import org.carth.common.lcm

class Day20(input: String) : Puzzle<Long, Long>() {

    private val allModules: Map<String, Module> = input.lines().map { line ->
        if (line.startsWith("broadcaster")) {
            val (name, value) = line.split(" -> ")
            Broadcaster(name, value.split(",").map { it.trim() })
        } else if (line.startsWith("%")) {
            val (name, value) = line.split(" -> ")
            FlipFlop(name.drop(1), value.split(",").map { it.trim() })
        } else if (line.startsWith("&")) {
            val (name, value) = line.split(" -> ")
            Conjunction(name.drop(1), value.split(",").map { it.trim() })
        } else {
            error("Invalid line $line")
        }
    }.associateBy { it.key }
        .also { map ->
            for (conjunction in map.values.filterIsInstance<Conjunction>()) {
                conjunction.memory = map.values.filter { module ->
                    module.modules.contains(conjunction.key)
                }.map { it.key }.associateWith { Pulse.LOW }.toMutableMap()
            }
        }

    override fun solvePartOne(): Long {
        var highCount = 0L
        var lowCount = 0L
        val steps = ArrayDeque<Step>()
        steps.add(Step("aptly", Pulse.LOW, allModules.getValue("broadcaster")))
        repeat(1000) {
            while (steps.isNotEmpty()) {
                val (source, pulse, target) = steps.removeFirst()
                if (pulse == Pulse.HIGH) highCount++ else lowCount++
                val res = target.sendPulse(source, pulse)
                for ((newPulse, newTarget) in res)
                    steps.add(Step(target.key, newPulse, allModules[newTarget] ?: Untyped(newTarget)))
            }
        }
        return lowCount * highCount
    }

    override fun solvePartTwo(): Long {
        /*
        var presses = 1L
        val steps = ArrayDeque<Step>()
        while (true) {
            steps.add(Step("aptly", Pulse.LOW, allModules.getValue("broadcaster")))
            while (steps.isNotEmpty()) {
                val (source, pulse, target) = steps.removeFirst()
                if (target.key == "rx" && pulse == Pulse.LOW) {
                    return presses
                }
                val res = target.sendPulse(source, pulse)
                for ((newPulse, newTarget) in res)
                    steps.add(Step(target.key, newPulse, allModules[newTarget] ?: Untyped(newTarget)))
                presses++
            }
        }
        return 0L*/

        val rxSource = allModules.values.single { "rx" in it.modules }.key
        val interesting = allModules.values.filter { rxSource in it.modules }.map { it.key }
        val occurrences = interesting.associateWith { arrayListOf<Long>() }
        var presses = 1L
        val steps = ArrayDeque<Step>()
        out@ while (true) {
            steps.add(Step("aptly", Pulse.LOW, allModules.getValue("broadcaster")))
            while (steps.isNotEmpty()) {
                val (source, pulse, target) = steps.removeFirst()
                if (target.key == "rx" && pulse == Pulse.LOW) break@out
                if (pulse == Pulse.HIGH && source in interesting) {
                    occurrences[source]!!.add(presses)
                    if (occurrences.values.all { it.size >= 2 })
                        return lcm(occurrences.values.map { (a, b) -> b - a })
                }
                val res = target.sendPulse(source, pulse)
                for ((newPulse, newTarget) in res)
                    steps.add(Step(target.key, newPulse, allModules[newTarget] ?: Untyped(newTarget)))
            }
            presses++
        }
        return -1L
    }
}

data class Step(val source: String, val pulse: Pulse, val module: Module)

enum class Pulse {
    HIGH, LOW
}

interface Module {
    val key: String
    val modules: List<String>
    fun sendPulse(source: String,pulse: Pulse): List<Pair<Pulse, String>>
}

class FlipFlop(override val key: String, override val modules: List<String>) : Module {
    private var on = false
    override fun sendPulse(source: String,pulse: Pulse) =
        if (pulse == Pulse.HIGH)
            emptyList()
        else {
            on = !on
            modules.map { (if (on) Pulse.HIGH else Pulse.LOW) to it }
        }
}

class Conjunction(override val key: String, override val modules: List<String>) : Module {
    var memory = mutableMapOf<String, Pulse>()

    override fun sendPulse(source: String,pulse: Pulse): List<Pair<Pulse, String>> {
        memory[source] = pulse
        return if (memory.all { it.value == Pulse.HIGH })
            modules.map { Pulse.LOW to it }
        else
            modules.map { Pulse.HIGH to it }
    }
}

class Broadcaster(override val key: String, override val modules: List<String>) : Module {
    override fun sendPulse(source: String,pulse: Pulse) = modules.map { pulse to it }
}

class Untyped(override val key: String, override val modules: List<String> = listOf()) : Module {
    override fun sendPulse(source: String,pulse: Pulse) = emptyList<Pair<Pulse, String>>()
}