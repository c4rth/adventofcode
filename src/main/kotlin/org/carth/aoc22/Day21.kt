package org.carth.aoc22

import org.carth.common.Puzzle


class Day21(private val data: List<String>) : Puzzle<Long, Long>() {

    override fun solvePartOne(): Long {
        val tree = Tree()
        data.forEach { line ->
            tree.parse(line)
        }
        return tree.find("root").evaluate()
    }


    // Not my solution
    override fun solvePartTwo(): Long {

        val tree = Tree()
        data.forEach { line ->
            tree.parse(line)
        }

        val rootLeft: String = tree.find("root").left
        val rootRight: String = tree.find("root").right

        val (branchHuman, branchNotHuman) = if (tree.contains(tree.find(rootRight), "humn"))
            rootLeft to rootRight
        else
            rootRight to rootLeft

        var current = tree.find(branchNotHuman)
        current.evaluate()

        var required: Long = tree.find(branchHuman).evaluate()
        while (current.id != "humn") {
            val left = tree.find(current.left)
            val right = tree.find(current.right)
            if (tree.contains(left, "humn")) {
                when (current.oper) {
                    "+" -> required -= right.evaluate()
                    "-" -> required += right.evaluate()
                    "*" -> required /= right.evaluate()
                    "/" -> required *= right.evaluate()
                }
                current = left
            } else {
                when (current.oper) {
                    "+" -> required -= left.evaluate()
                    "-" -> required = left.evaluate() - required
                    "*" -> required /= left.evaluate()
                    "/" -> required *= left.evaluate()
                }
                current = right
            }
        }
        return required
    }

    class Tree {
        private val monkeys = mutableListOf<Monkey>()
        fun find(searchId: String): Monkey = monkeys.find { it.id == searchId }!!

        fun add(monkey: Monkey) = monkeys.add(monkey)

        fun contains(monkey: Monkey, id: String): Boolean {
            return if (monkey.id == id)
                true
            else if (monkey.left.isEmpty() && monkey.right.isEmpty())
                false
            else
                contains(find(monkey.left), id) || contains(find(monkey.right), id)
        }

        fun parse(str: String) {
            val (monkey, op) = str.split(": ")
            monkeys.add(
                if (op.first().isDigit()) {
                    Monkey(this, monkey, op.toLong())
                } else {
                    val (opn1, opr, opn2) = op.split(" ")
                    Monkey(this, monkey, opn1, opr, opn2)
                }
            )
        }
    }

    class Monkey(
        private val tree: Tree,
        val id: String,
        private var value: Long,
        private var hasValue: Boolean,
        val left: String,
        val oper: String,
        val right: String
    ) {

        constructor(tree: Tree, id: String, value: Long) :
                this(tree, id, value, true, "", "", "")
        constructor(tree: Tree, id: String, op1: String, opd: String, op2: String) :
                this(tree, id, 0L, false, op1, opd, op2)

        fun evaluate(): Long {
            return if (hasValue) {
                value
            } else {
                value = when (oper) {
                    "+" -> tree.find(left).evaluate() + tree.find(right).evaluate()
                    "-" -> tree.find(left).evaluate() - tree.find(right).evaluate()
                    "*" -> tree.find(left).evaluate() * tree.find(right).evaluate()
                    "/" -> tree.find(left).evaluate() / tree.find(right).evaluate()
                    else -> throw RuntimeException("invalid operation $oper")
                }
                hasValue = true
                value
            }
        }

    }

}