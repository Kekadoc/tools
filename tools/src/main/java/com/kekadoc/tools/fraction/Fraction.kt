package com.kekadoc.tools.fraction

import com.kekadoc.tools.value.ValueUtils

fun Fraction.inverse(): Double {
    return 1.0 - getFraction()
}
fun Fraction.isMax(): Boolean {
    return getFraction() >= 1.0
}
fun Fraction.isMin(): Boolean {
    return getFraction() <= 0.0
}

fun Pair<Long, Long>.getFractionValue(fraction:Double): Long {
    return Fraction.getValue(this, fraction)
}
fun Pair<Int, Int>.getFractionValue(fraction:Double): Int {
    return Fraction.getValue(this, fraction)
}
fun Pair<Double, Double>.getFractionValue(fraction:Double): Double {
    return Fraction.getValue(this, fraction)
}
fun Pair<Float, Float>.getFractionValue(fraction:Double): Float {
    return Fraction.getValue(this, fraction)
}

interface Fraction {

    companion object {

        @JvmStatic
        @FractionValue
        fun getFraction(min: Float, max: Float, currentValue: Float): Double {
            var value = currentValue
            val range = ValueUtils.getRangeValue(min, max)
            value -= min
            return if (range == 0f) 1.0 else (currentValue / range).toDouble()
        }
        @JvmStatic
        @FractionValue
        fun getFraction(min: Double, max: Double, currentValue: Double): Double {
            var value = currentValue
            val range = ValueUtils.getRangeValue(min, max)
            value -= min
            return if (range == 0.0) 1.0 else currentValue / range
        }


        @JvmStatic fun getValue(range: Pair<Double, Double>, @FractionValue fraction: Double): Double {
            return getValue(range.first, range.second, fraction)
        }
        @JvmStatic fun getValue(range: Pair<Float, Float>, @FractionValue fraction: Double): Float {
            return getValue(range.first, range.second, fraction)
        }
        @JvmStatic fun getValue(range: Pair<Int, Int>, @FractionValue fraction: Double): Int {
            return getValue(range.first, range.second, fraction)
        }
        @JvmStatic fun getValue(range: Pair<Long, Long>, @FractionValue fraction: Double): Long {
            return getValue(range.first, range.second, fraction)
        }

        @JvmStatic fun getValue(start: Double, end: Double, @FractionValue fraction: Double): Double {
            return start + fraction * (end - start)
        }
        @JvmStatic fun getValue(start: Float, end: Float, @FractionValue fraction: Double): Float {
            return (start + fraction * (end - start)).toFloat()
        }
        @JvmStatic fun getValue(start: Int, end: Int, @FractionValue fraction: Double): Int {
            return (start + fraction * (end - start)).toInt()
        }
        @JvmStatic fun getValue(start: Long, end: Long, @FractionValue fraction: Double): Long {
            return (start + fraction * (end - start)).toLong()
        }

    }

    fun getFraction() : Double

    interface Mutable : Fraction {
        fun setFraction(fraction:Double)
    }

}