package com.kekadoc.tools.fraction

import com.kekadoc.tools.value.ValueUtils

typealias FractionValue = Double

fun Fraction.inverse(): FractionValue {
    return 1.0 - getFraction()
}
fun Fraction.isMax(): Boolean {
    return getFraction() >= 1.0
}
fun Fraction.isMin(): Boolean {
    return getFraction() <= 0.0
}

fun Pair<Long, Long>.getFractionValue(fraction: FractionValue): Long {
    return Fraction.getValue(this, fraction)
}
fun Pair<Int, Int>.getFractionValue(fraction: FractionValue): Int {
    return Fraction.getValue(this, fraction)
}
fun Pair<Double, Double>.getFractionValue(fraction: FractionValue): Double {
    return Fraction.getValue(this, fraction)
}
fun Pair<Float, Float>.getFractionValue(fraction: FractionValue): Float {
    return Fraction.getValue(this, fraction)
}

interface Fraction {

    companion object {

        @JvmStatic
        @com.kekadoc.tools.annotations.FractionValue
        fun getFraction(min: Float, max: Float, currentValue: Float): FractionValue {
            var value = currentValue
            val range = ValueUtils.getRangeValue(min, max)
            value -= min
            return if (range == 0f) 1.0 else (currentValue / range).toDouble()
        }
        @JvmStatic
        @com.kekadoc.tools.annotations.FractionValue
        fun getFraction(min: Double, max: Double, currentValue: Double): FractionValue {
            var value = currentValue
            val range = ValueUtils.getRangeValue(min, max)
            value -= min
            return if (range == 0.0) 1.0 else currentValue / range
        }


        @JvmStatic fun getValue(range: Pair<Double, Double>, @com.kekadoc.tools.annotations.FractionValue fraction: FractionValue): Double {
            return getValue(range.first, range.second, fraction)
        }
        @JvmStatic fun getValue(range: Pair<Float, Float>, @com.kekadoc.tools.annotations.FractionValue fraction: FractionValue): Float {
            return getValue(range.first, range.second, fraction)
        }
        @JvmStatic fun getValue(range: Pair<Int, Int>, @com.kekadoc.tools.annotations.FractionValue fraction: FractionValue): Int {
            return getValue(range.first, range.second, fraction)
        }
        @JvmStatic fun getValue(range: Pair<Long, Long>, @com.kekadoc.tools.annotations.FractionValue fraction: FractionValue): Long {
            return getValue(range.first, range.second, fraction)
        }

        @JvmStatic fun getValue(start: Double, end: Double, @com.kekadoc.tools.annotations.FractionValue fraction: FractionValue): Double {
            return start + fraction * (end - start)
        }
        @JvmStatic fun getValue(start: Float, end: Float, @com.kekadoc.tools.annotations.FractionValue fraction: FractionValue): Float {
            return (start + fraction * (end - start)).toFloat()
        }
        @JvmStatic fun getValue(start: Int, end: Int, @com.kekadoc.tools.annotations.FractionValue fraction: FractionValue): Int {
            return (start + fraction * (end - start)).toInt()
        }
        @JvmStatic fun getValue(start: Long, end: Long, @com.kekadoc.tools.annotations.FractionValue fraction: FractionValue): Long {
            return (start + fraction * (end - start)).toLong()
        }

    }

    fun getFraction() : FractionValue

    interface Mutable : Fraction {
        fun setFraction(fraction: FractionValue)
    }

}