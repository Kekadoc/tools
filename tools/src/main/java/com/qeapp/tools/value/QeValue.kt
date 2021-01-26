package com.qeapp.tools.value

import kotlin.math.pow
import kotlin.math.roundToInt

object QeValue {

    /** Округляет полученное число, до countZero знаков после запятой  */
    @JvmStatic fun round(value: Float, countZero: Int): Float {
        val zero: Int = 10.0.pow(countZero).toInt()
        return (value * zero).roundToInt().toFloat() / zero
    }

    /** Округляет полученное число, до countZero знаков после запятой  */
    @JvmStatic fun round(value: Double, countZero: Int): Double {
        val zero: Int = 10.0.pow(countZero).toInt()
        return (value * zero).roundToInt().toDouble() / zero
    }

    @JvmStatic fun getRange(from: Int, to: Int): Int {
        return to - from
    }

    @JvmStatic fun average(vararg floats: Float): Float {
        var s = 0.0f
        for (p in floats) s += p
        return s / floats.size
    }
    @JvmStatic fun average(vararg ints: Int): Int {
        var s = 0
        for (p in ints) s += p
        return s / ints.size
    }
    @JvmStatic fun average(vararg doubles: Double): Double {
        var s = 0.0
        for (p in doubles) s += p
        return s / doubles.size
    }
    @JvmStatic fun average(vararg longs: Long): Long {
        var s: Long = 0
        for (p in longs) s += p
        return s / longs.size
    }


    @JvmStatic fun <T> averageFloat(items: Collection<T>, processor: (item: T) -> Float): Float {
        var s = 0f
        for (item in items) s += processor.invoke(item)
        return s / items.size
    }
    @JvmStatic fun <T> averageFloat(processor: (item: T) -> Float, vararg items: T): Float {
        var s = 0f
        for (item in items) s += processor.invoke(item)
        return s / items.size
    }

    @JvmStatic fun <T> averageInt(items: Collection<T>, processor: (item: T) -> Int): Int {
        var s = 0
        for (item in items) s += processor.invoke(item)
        return s / items.size
    }
    @JvmStatic fun <T> averageInt(processor: (item: T) -> Int, vararg items: T): Int {
        var s = 0
        for (item in items) s += processor.invoke(item)
        return s / items.size
    }

    @JvmStatic fun <T> averageDouble(items: Collection<T>, processor: (item: T) -> Double): Double {
        var s = 0.0
        for (item in items) s += processor.invoke(item)
        return s / items.size
    }
    @JvmStatic fun <T> averageDouble(processor: (item: T) -> Double, vararg items: T): Double {
        var s = 0.0
        for (item in items) s += processor.invoke(item)
        return s / items.size
    }

    @JvmStatic fun <T> averageLong(items: Collection<T>, processor: (item: T) -> Long): Long {
        var s = 0L
        for (item in items) s += processor.invoke(item)
        return s / items.size
    }
    @JvmStatic fun <T> averageLong(processor: (item: T) -> Long, vararg items: T): Long {
        var s = 0L
        for (item in items) s += processor.invoke(item)
        return s / items.size
    }

    @JvmStatic fun makePercent(vararg percent: Float): FloatArray {
        var sum = 0f
        for (p in percent) sum += p
        val make = FloatArray(percent.size)
        for (i in percent.indices) make[i] = percent[i] / sum * 100f
        return make
    }

    @JvmStatic fun convertToAnotherPercent(percent: Float, anotherPercent: Float): Float {
        return percent * (anotherPercent / 100f)
    }

    @JvmStatic fun boolEquals(bool: Boolean, valBool: Int): Boolean {
        if (valBool > 1 || valBool < 0) return false
        return if (bool && valBool == 1) true else !bool && valBool == 0
    }
    @JvmStatic fun boolVal(bool: Boolean): Int {
        return if (bool) 1 else 0
    }

}