package com.kekadoc.tools.fraction

import com.kekadoc.tools.value.ValueUtils

fun Double.toFraction(): Fraction = fractionOf(this)
fun Double.toMutableFraction(): Fraction = mutableFractionOf(this)
fun Double.toObservableFraction(): Fraction = observableFractionOf(this)

fun fractionOf(@FractionValue fraction: Double): Fraction {
    return object : Fraction {
        override fun getFraction(): Double {
            return fraction
        }
    }
}
fun mutableFractionOf(@FractionValue fraction: Double): Fraction.Mutable {
    return object : Fraction.Mutable {
        private var frac = fraction
        override fun getFraction(): Double {
            return frac
        }
        override fun setFraction(fraction: Double) {
            frac = fraction
        }
    }
}
fun observableFractionOf(@FractionValue fraction: Double): ObservableFraction {
    return FractionObject(fraction)
}

fun Fraction.inverse(): Double {
    return 1.0 - getFraction()
}
fun Fraction.isMax(): Boolean {
    return getFraction() >= 1.0
}
fun Fraction.isMin(): Boolean {
    return getFraction() <= 0.0
}

fun Fraction.Mutable.change(newFraction: Double, observer: FractionObserver? = null): Double {
    return Fraction.change(this, newFraction, observer)
}
fun Fraction.Mutable.add(adding: Double, observer: FractionObserver? = null): Double {
    return Fraction.add(this, adding, observer)
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

        fun adjustValue(fraction: Double, events: ValueUtils.RangeChangeEvents<Double>? = null): Double {
            return ValueUtils.valueInRange(0.0, 1.0, fraction, events)
        }
        fun adjustValue(fraction: Double, events: ValueUtils.RangeChangeEventsBuilder<Double>.() -> Unit): Double {
            return ValueUtils.valueInRange(0.0, 1.0, fraction, events)
        }

        fun change(fraction: Mutable, newFraction: Double, observer: FractionObserver? = null): Double {
            return ValueUtils.valueInRange(0.0, 1.0, newFraction) {
                onChange = {_, newValue ->
                    val old = fraction.getFraction()
                    fraction.setFraction(newValue)
                    observer?.onFractionChange(fraction, old, newValue)
                }
            }
        }
        fun add(fraction: Mutable, adding: Double, observer: FractionObserver? = null): Double {
            return ValueUtils.setValueInRange(0.0, 1.0, fraction.getFraction(), adding) {
                onChange = {oldValue, newValue ->
                    val old = fraction.getFraction()
                    fraction.setFraction(newValue)
                    observer?.onFractionChange(fraction, old, newValue)
                }
            }
        }

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