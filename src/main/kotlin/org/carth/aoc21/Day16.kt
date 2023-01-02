package org.carth.aoc21

import org.carth.common.Puzzle

class Day16(input: String) : Puzzle<Int, Long>() {

    private val binary =
        input.map { c -> Integer.parseInt(c.toString(), 16).toString(2).padStart(4, '0') }.joinToString("")
    private val packets = parse(Bits(binary))

    override fun solvePartOne() = packets.sumOfVersion()

    override fun solvePartTwo() = packets.value()

    private fun parse(bits: Bits, packet: Packet? = null): Packet {
        val version = bits.readInt(3)
        val type = bits.readInt(3)
        var current = packet
        if (type == 4) {
            var number = ""
            do {
                val part = bits.read(5)
                number += part.substring(1)
            } while (part[0] == '1')
            val litteral = Literal(version, number.toLong(2))
            if (current == null) {
                current = litteral
            } else {
                (current as Operator).subPackets += litteral
            }
        } else {
            val operator = Operator(version, type)
            (current as Operator?)?.subPackets?.add(operator)
            current = operator
            if (bits.readInt(1) == 0) {
                val length = bits.readInt(15)
                val readStart = bits.index
                while (bits.index - readStart < length) {
                    parse(bits, current)
                }
            } else {
                val length = bits.readInt(11)
                repeat(length) {
                    parse(bits, current)
                }
            }
        }
        return current
    }

    abstract class Packet(val version: Int) {
        abstract fun sumOfVersion(): Int
        abstract fun value(): Long
    }

    class Literal(version: Int, private val value: Long) : Packet(version) {
        override fun sumOfVersion() = version
        override fun value() = value
    }

    class Operator(version: Int, private val type: Int, val subPackets: MutableList<Packet> = mutableListOf()) :
        Packet(version) {
        override fun sumOfVersion() = version + subPackets.sumOf { it.sumOfVersion() }

        override fun value() = when (type) {
            0 -> subPackets.sumOf { it.value() }
            1 -> subPackets.fold(1L) { acc, subPacket -> acc * subPacket.value() }
            2 -> subPackets.minOf { it.value() }
            3 -> subPackets.maxOf { it.value() }
            5 -> if (subPackets[0].value() > subPackets[1].value()) 1 else 0
            6 -> if (subPackets[0].value() < subPackets[1].value()) 1 else 0
            7 -> if (subPackets[0].value() == subPackets[1].value()) 1 else 0
            else -> throw Exception("unknown type : $type")
        }
    }
//
//    class Packet(private val version: Int, private val type: Int, private val value: Long = 0, val subPackets: MutableList<Packet> = mutableListOf()) {
//        val versionSum: Int
//            get() = version + subPackets.sumOf { it.versionSum }
//
//        fun eval(): Long = when (type) {
//            0 -> subPackets.sumOf { it.eval() }
//            1 -> subPackets.fold(1L) { acc, subEval -> acc * subEval.eval() }
//            2 -> subPackets.minOf { it.eval() }
//            3 -> subPackets.maxOf { it.eval() }
//            4 -> value
//            5 -> subPackets.let { (subPacket1, subPacket2) -> if (subPacket1.eval() > subPacket2.eval()) 1 else 0 }
//            6 -> subPackets.let { (subPacket1, subPacket2) -> if (subPacket1.eval() < subPacket2.eval()) 1 else 0 }
//            7 -> subPackets.let { (subPacket1, subPacket2) -> if (subPacket1.eval() == subPacket2.eval()) 1 else 0 }
//            else -> throw Exception("unknown type : $type")
//        }
//    }

    class Bits(private val str: String) {
        private var _index = 0
        val index: Int
            get() = _index

        fun read(length: Int): String {
            val s = str.substring(_index, _index + length)
            _index += length
            return s
        }

        fun readInt(length: Int): Int {
            val s = str.substring(_index, _index + length)
            _index += length
            return s.toInt(2)
        }
    }
}