package org.carth.aoc21

import org.carth.common.Puzzle
import java.lang.Integer.max
import kotlin.math.ceil
import kotlin.math.floor

class Day18(input: String) : Puzzle<Int, Int>() {

    private val data = input.lines()

    override fun solvePartOne(): Int {
        var node = parse(data.first())
        data.drop(1).forEach { line ->
            node = node.add(parse(line))
            node.reduce()
        }
        return node.magnitude()
    }

    override fun solvePartTwo(): Int {
        var max = 0
        for (i in data.indices) {
            for (j in data.indices) {
                if (i != j) {
                    max = max(max, parse(data[i]).add(parse(data[j])).reduce().magnitude())
                }
            }
        }
        return max
    }

    private fun getCommaIndex(str: String): Int {
        var count = 0
        for (i in str.indices) {
            when (str[i]) {
                '[' -> count++
                ']' -> count--
                ',' -> if (count == 1) return i
            }
        }
        return -1
    }

    private fun parse(str: String): Node =
        if (str[0] == '[') {
            val index = getCommaIndex(str)
            Node.Paire(parse(str.take(index).drop(1)), parse(str.drop(index + 1).dropLast(1)))
        } else {
            Node.Regular(str.toInt())
        }


    sealed class Node(var parent: Paire? = null) {

        fun add(other: Node): Node = Paire(this, other)

        abstract fun leftValue(): Regular

        abstract fun rightValue(): Regular

        abstract fun findToExplode(depth: Int): Paire?

        abstract fun findToSplit(): Regular?

        abstract fun magnitude(): Int

        fun reduce(): Node {
            do {
                val node = findToExplode(0)?.also {
                    it.explode()
                } ?: findToSplit()?.also {
                    it.split()
                }
            } while (node != null)
            return this
        }

        data class Paire(var left: Node, var right: Node) : Node() {
            init {
                left.parent = this
                right.parent = this
            }

            override fun leftValue(): Regular = left.leftValue()

            override fun rightValue(): Regular = right.rightValue()

            override fun magnitude() = left.magnitude() * 3 + right.magnitude() * 2

            override fun findToExplode(depth: Int): Paire? =
                if (depth == 4) this else left.findToExplode(depth + 1) ?: right.findToExplode(depth + 1)

            override fun findToSplit(): Regular? = left.findToSplit() ?: right.findToSplit()

            fun replace(old: Node, new: Node) {
                if (left === old) left = new else right = new
                new.parent = this
            }

            private fun findParentLeft(): Paire? = if (parent?.left !== this) parent else parent?.findParentLeft()

            private fun findParentRight(): Paire? = if (parent?.right !== this) parent else parent?.findParentRight()

            fun explode() {
                findParentLeft()?.left?.rightValue()?.let {
                    it.value += (left as Regular).value
                }
                findParentRight()?.right?.leftValue()?.let {
                    it.value += (right as Regular).value
                }
                parent?.replace(this, Regular(0))
            }
        }

        data class Regular(var value: Int) : Node() {
            override fun leftValue(): Regular = this

            override fun rightValue(): Regular = this

            override fun magnitude(): Int = value

            override fun findToExplode(depth: Int): Paire? = null

            override fun findToSplit(): Regular? = if (value >= 10) this else null

            fun split() =
                parent?.replace(this, Paire(Regular(floor(value / 2.0).toInt()), Regular(ceil(value / 2.0).toInt())))
        }
    }
}

