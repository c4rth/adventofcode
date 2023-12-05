package org.carth.aoc2023

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import org.carth.common.Puzzle
import java.util.concurrent.atomic.AtomicLong
import kotlin.math.min
import kotlin.streams.asStream


class Day05(input: String) : Puzzle<Long, Long>() {

    private val seedsLine = input.split(System.lineSeparator() + System.lineSeparator())[0]

    private val allMaps = input.split(System.lineSeparator() + System.lineSeparator()).drop(1).map {
        it.split(System.lineSeparator()).drop(1).map {
            val (to, from, range) = it.split(" ").map(String::toLong)
            from..<(from + range) to to..<(to + range)
        }
    }

    override fun solvePartOne(): Long {
        val seeds = seedsLine.split(" ").drop(1).map(String::toLong).toMutableList()
        allMaps.forEach { maps ->
            seeds.forEachIndexed { index, seed ->
                maps.forEach { (from, to) ->
                    if (seed in from) {
                        seeds[index] = seed + (to.first - from.first)
                    }
                }
            }
        }
        return seeds.min()
    }

    private suspend fun findForRange(range: LongRange, result: AtomicLong) {
        logger.info { "range: $range" }
        coroutineScope {
            async {
                range.asSequence().asStream().parallel().forEach { seed ->
                    var newSeed = seed
                    allMaps.forEach maps@{ maps ->
                        maps.forEach { (from, to) ->
                            if (newSeed in from) {
                                newSeed += (to.first - from.first)
                                return@maps
                            }
                        }
                    }
                    result.getAndAccumulate(newSeed, ::min)
                }
            }.await()
        }
    }

    private suspend fun findSeedPosition(ranges: List<LongRange>, result: AtomicLong) {
        coroutineScope {
            ranges.map { range ->
                async {
                    findForRange(range, result)
                }
            }.awaitAll()
        }
    }

    override fun solvePartTwo(): Long {
        val result = AtomicLong(Long.MAX_VALUE)
        val ranges = seedsLine.split(" ").drop(1)
            .map(String::toLong)
            .chunked(2)
            .map { (start, range) -> (start..<(start + range)) }
        runBlocking {
            findSeedPosition(ranges, result)
        }
        return result.get()
    }

}