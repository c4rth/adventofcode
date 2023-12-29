package org.carth.aoc2023

import com.microsoft.z3.BitVecNum
import com.microsoft.z3.BitVecSort
import com.microsoft.z3.Context
import com.microsoft.z3.Expr
import org.apache.commons.geometry.euclidean.twod.Line
import org.apache.commons.geometry.euclidean.twod.Lines
import org.apache.commons.geometry.euclidean.twod.Vector2D
import org.apache.commons.numbers.core.Precision
import org.carth.common.Puzzle
import java.math.BigInteger

class Day24(input: String) : Puzzle<Long, BigInteger>() {

    private val hails = input.split(System.lineSeparator()).map { line ->
        val split = line.split(" @ ")
        val p = split[0].split(",").map { it.trim().toDouble() }
        val v = split[1].split(",").map { it.trim().toDouble() }
        Hail(Point3dDouble(p[0], p[1], p[2]), Point3dDouble(v[0], v[1], v[2]))
    }

    override fun solvePartOne(): Long {
        var total = 0L
        val range = args[0] as ClosedFloatingPointRange<Double>
        for (idx1 in hails.indices) {
            val hail1 = hails[idx1]
            for (idx2 in idx1 + 1..<hails.size) {
                val hail2 = hails[idx2]
                val intersect = hail1.intersectXY(hail2)
                if (intersect != null) {
                    if (intersect.x in range && intersect.y in range && hail1.isFutureValue(intersect.x) && hail2.isFutureValue(intersect.x) )
                        total++

                }
            }
        }
        return total
    }

    override fun solvePartTwo(): BigInteger {
        Context().use { ctx ->
            val solver = ctx.mkSolver()

            val longType = ctx.mkBitVecSort(64)

            fun variableOf(name: String) = ctx.mkConst(name, longType)

            fun valueOf(value: Long) = ctx.mkNumeral(value, longType) as BitVecNum

            operator fun Expr<BitVecSort>.times(t: Expr<BitVecSort>) = ctx.mkBVMul(this, t)

            operator fun Expr<BitVecSort>.plus(t: Expr<BitVecSort>) = ctx.mkBVAdd(this, t)

            infix fun Expr<BitVecSort>.equalTo(t: Expr<BitVecSort>) = ctx.mkEq(this, t)

            infix fun Expr<BitVecSort>.greaterThan(t: Expr<BitVecSort>) = ctx.mkBVSGT(this, t)

            val zero = valueOf(0)

            val x = variableOf("x")
            val y = variableOf("y")
            val z = variableOf("z")
            val dx = variableOf("dx")
            val dy = variableOf("dy")
            val dz = variableOf("dz")

            hails.take(3).forEachIndexed { index, hail ->
                val t = variableOf("t_$index")

                val (posX, posY, posZ) = hail.position.toZ3Numerals(::valueOf)
                val (velX, velY, velZ) = hail.velocity.toZ3Numerals(::valueOf)

                solver.add(t greaterThan zero)
                solver.add(x + dx * t equalTo posX + velX * t)
                solver.add(y + dy * t equalTo posY + velY * t)
                solver.add(z + dz * t equalTo posZ + velZ * t)
            }

            solver.check()

            val model = solver.model

            val xEval = model.evaluate(x, true) as BitVecNum
            val yEval = model.evaluate(y, true) as BitVecNum
            val zEval = model.evaluate(z, true) as BitVecNum

            return xEval.bigInteger + yEval.bigInteger + zEval.bigInteger
        }
    }

    data class Hail(val position: Point3dDouble, val velocity: Point3dDouble) {

        fun intersectXY(hail: Hail): Vector2D? = lineXY.intersection(hail.lineXY)

        fun isFutureValue(x: Double): Boolean = !(velocity.x < 0.0 && x > position.x || velocity.x > 0.0 && x < position.x)

        private val lineXY: Line by lazy {
            Lines.fromPointAndDirection(
                Vector2D.of(position.x, position.y),
                Vector2D.of(velocity.x, velocity.y),
                Precision.doubleEquivalenceOfEpsilon(1e-10)
            )
        }
    }

    data class Point3dDouble(val x: Double, val y: Double, val z: Double) {

        fun toZ3Numerals(valueOf: (Long) -> BitVecNum): Triple<BitVecNum, BitVecNum, BitVecNum> {
            return Triple(valueOf(x.toLong()), valueOf(y.toLong()), valueOf(z.toLong()))
        }

    }
}