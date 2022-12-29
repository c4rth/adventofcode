package org.carth.aoc22

import org.carth.common.Puzzle
import kotlin.math.ceil
import kotlin.math.max
import kotlin.math.min


class Day19(data: List<String>) : Puzzle<Int, Int>() {

    private val blueprints = data.map { line ->
        line.split(" ").let {
            Blueprint(
                listOf(
                    RobotCost(ore = it[6].toInt()),
                    RobotCost(ore = it[12].toInt()),
                    RobotCost(ore = it[18].toInt(), clay = it[21].toInt()),
                    RobotCost(ore = it[27].toInt(), obsidian = it[30].toInt())
                )
            )
        }
    }

    override fun solvePartOne(): Int {
        return blueprints.mapIndexed { index, blueprint ->
            BlueprintSimulator(blueprint, 24).getMostGeodes() * (index + 1)
        }.sum()
    }

    override fun solvePartTwo(): Int {
        return blueprints.take(3).map { BlueprintSimulator(it, 32).getMostGeodes() }.reduce(Int::times)
    }

    data class Blueprint(val costs: List<RobotCost>)

    data class RobotCost(val ore: Int = 0, val clay: Int = 0, val obsidian: Int = 0)

    class BlueprintSimulator(private val blueprint: Blueprint, private val duration: Int) {

        private var best = 0

        fun getMostGeodes(): Int {
            return simulate(SimulationState(0, 0, 0, 0, 0, 1, 0, 0, 0))
        }

        private fun simulate(state: SimulationState): Int {
            if (state.isOver(duration)) {
                best = max(state.geodes, best)
                return state.geodes
            }
            if (state.pruneable(duration, best)) {
                return 0
            }
            var max = 0
            if (state.obsidianProd > 0) {
                val result = simulate(buildGeodeBot(state))
                max = max(result, max)
            }
            if (state.clayProd > 0) {
                val result = simulate(buildObsidianBot(state))
                max = max(result, max)
            }
            if (state.oreProd > 0) {
                val result = simulate(buildClayBot(state))
                max = max(result, max)
            }
            if (state.oreProd > 0) {
                val result = simulate(buildOreBot(state))
                max = max(result, max)
            }
            return max
        }

        private fun buildGeodeBot(state: SimulationState): SimulationState {
            val oresNeeded = max(0, blueprint.costs[3].ore - state.ore)
            val obsidiansNeeded = max(0, blueprint.costs[3].obsidian - state.obsidian)
            val wait = if (oresNeeded == 0 && obsidiansNeeded == 0) {
                1
            } else {
                max(
                    ceil(oresNeeded.toFloat() / state.oreProd.toFloat()).toInt(),
                    ceil(obsidiansNeeded.toFloat() / state.obsidianProd.toFloat()).toInt(),
                ) + 1
            }
            return state.progress(wait, duration).let {
                it.copy(
                    ore = it.ore - blueprint.costs[3].ore,
                    obsidian = it.obsidian - blueprint.costs[3].obsidian,
                    geodesProd = it.geodesProd + 1
                )
            }
        }

        private fun buildObsidianBot(state: SimulationState): SimulationState {
            val oresNeeded = max(0, blueprint.costs[2].ore - state.ore)
            val clayNeeded = max(0, blueprint.costs[2].clay - state.clay)
            val wait = if (oresNeeded == 0 && clayNeeded == 0)
                1
            else {
                max(
                    ceil(oresNeeded.toFloat() / state.oreProd.toFloat()).toInt(),
                    ceil(clayNeeded.toFloat() / state.clayProd.toFloat()).toInt()
                ) + 1
            }
            return state.progress(wait, duration).let {
                it.copy(
                    ore = it.ore - blueprint.costs[2].ore,
                    clay = it.clay - blueprint.costs[2].clay,
                    obsidianProd = it.obsidianProd + 1
                )
            }
        }

        private fun buildClayBot(state: SimulationState): SimulationState {
            val oresNeeded = max(0, blueprint.costs[1].ore - state.ore)
            val wait = if (oresNeeded == 0)
                1
            else ceil(oresNeeded.toFloat() / state.oreProd.toFloat()).toInt() + 1
            return state.progress(wait, duration).let {
                it.copy(
                    ore = it.ore - blueprint.costs[1].ore,
                    clayProd = it.clayProd + 1
                )
            }
        }

        private fun buildOreBot(state: SimulationState): SimulationState {
            val oresNeeded = max(0, blueprint.costs[0].ore - state.ore)
            val wait = if (oresNeeded == 0)
                1
            else ceil(oresNeeded.toFloat() / state.oreProd.toFloat()).toInt() + 1
            return state.progress(wait, duration).let {
                it.copy(
                    ore = it.ore - blueprint.costs[0].ore,
                    oreProd = it.oreProd + 1
                )
            }
        }

    }


    data class SimulationState(
        val minute: Int,
        val ore: Int,
        val clay: Int,
        val obsidian: Int,
        val geodes: Int,
        val oreProd: Int,
        val clayProd: Int,
        val obsidianProd: Int,
        val geodesProd: Int,
    ) {
        fun isOver(duration: Int): Boolean = minute >= duration

        fun pruneable(duration: Int, best: Int): Boolean =
            geodes + (0 until duration - minute).sumOf { geodesProd + it } < best

        //returns the next simulation state after specified minutes have passed
        fun progress(minutes: Int, duration: Int): SimulationState {
            val time = min(duration - this.minute, minutes)

            return this.copy(
                minute = minute + time,
                ore = ore + oreProd * time,
                clay = clay + clayProd * time,
                obsidian = obsidian + obsidianProd * time,
                geodes = geodes + geodesProd * time
            )
        }
    }

}
