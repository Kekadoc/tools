package com.kekadoc.tools.value

import com.kekadoc.tools.fraction.Fraction
import com.kekadoc.tools.fraction.FractionObserver
import com.kekadoc.tools.fraction.ObservableFraction
import com.kekadoc.tools.observer.ObserverManagement
import com.kekadoc.tools.observer.Observing

object ValueRangeUtils {

    fun toInt(range: ValueRangeLong): ValueRangeInt {
        return ValueRangeIntImpl(range.getValue().toInt(), range.getMin().toInt(), range.getMax().toInt())
    }
    fun toInt(range: ValueRangeFloat): ValueRangeInt {
        return ValueRangeIntImpl(range.getValue().toInt(), range.getMin().toInt(), range.getMax().toInt())
    }
    fun toInt(range: ValueRangeDouble): ValueRangeInt {
        return ValueRangeIntImpl(range.getValue().toInt(), range.getMin().toInt(), range.getMax().toInt())
    }
    fun toLong(range: ValueRangeInt): ValueRangeLong {
        return ValueRangeLongImpl(range.getValue().toLong(), range.getMin().toLong(), range.getMax().toLong())
    }
    fun toLong(range: ValueRangeFloat): ValueRangeLong {
        return ValueRangeLongImpl(range.getValue().toLong(), range.getMin().toLong(), range.getMax().toLong())
    }
    fun toLong(range: ValueRangeDouble): ValueRangeLong {
        return ValueRangeLongImpl(range.getValue().toLong(), range.getMin().toLong(), range.getMax().toLong())
    }
    fun toFloat(range: ValueRangeInt): ValueRangeFloat {
        return ValueRangeFloatImpl(range.getValue().toFloat(), range.getMin().toFloat(), range.getMax().toFloat())
    }
    fun toFloat(range: ValueRangeLong): ValueRangeFloat {
        return ValueRangeFloatImpl(range.getValue().toFloat(), range.getMin().toFloat(), range.getMax().toFloat())
    }
    fun toFloat(range: ValueRangeDouble): ValueRangeFloat {
        return ValueRangeFloatImpl(range.getValue().toFloat(), range.getMin().toFloat(), range.getMax().toFloat())
    }
    fun toDouble(range: ValueRangeInt): ValueRangeDouble {
        return ValueRangeDoubleImpl(range.getValue().toDouble(), range.getMin().toDouble(), range.getMax().toDouble())
    }
    fun toDouble(range: ValueRangeLong): ValueRangeDouble {
        return ValueRangeDoubleImpl(range.getValue().toDouble(), range.getMin().toDouble(), range.getMax().toDouble())
    }
    fun toDouble(range: ValueRangeFloat): ValueRangeDouble {
        return ValueRangeDoubleImpl(range.getValue().toDouble(), range.getMin().toDouble(), range.getMax().toDouble())
    }

}

interface ValueRangeDouble : ValueDouble, Fraction {

    interface Mutable : ValueRangeDouble, ValueDouble.Mutable {
        fun setMax(max: Double)
        fun setMin(min: Double)
    }

    fun getMax(): Double
    fun getMin(): Double

    fun getRange(): Double {
        return  getMax() - getMin()
    }

    override fun getFraction(): Double {
        return Fraction.getFraction(getMin(), getMax(), getValue())
    }

}
interface ValueRangeFloat : ValueFloat, Fraction {

    interface Mutable : ValueRangeFloat, ValueFloat.Mutable {
        fun setMax(max: Float)
        fun setMin(min: Float)
    }

    fun getMax(): Float
    fun getMin(): Float

    fun getRange(): Float {
        return  getMax() - getMin()
    }

    override fun getFraction(): Double {
        return Fraction.getFraction(getMin().toDouble(), getMax().toDouble(), getValue().toDouble())
    }

}
interface ValueRangeLong : ValueLong, Fraction {

    interface Mutable : ValueRangeLong, ValueLong.Mutable {
        fun setMax(max: Long)
        fun setMin(min: Long)
    }

    fun getMax(): Long
    fun getMin(): Long

    fun getRange(): Long {
        return  getMax() - getMin()
    }

    override fun getFraction(): Double {
        return Fraction.getFraction(getMin().toDouble(), getMax().toDouble(), getValue().toDouble())
    }

}
interface ValueRangeInt : ValueInt, Fraction {

    interface Mutable : ValueRangeInt, ValueInt.Mutable {
        fun setMax(max: Int)
        fun setMin(min: Int)
    }

    fun getMax(): Int
    fun getMin(): Int

    fun getRange(): Int {
        return  getMax() - getMin()
    }

    override fun getFraction(): Double {
        return Fraction.getFraction(getMin().toDouble(), getMax().toDouble(), getValue().toDouble())
    }

}

interface ObservableValueRangeDouble : ValueRangeDouble {

    interface Observer {

        fun onValueChange(observable: ObservableValueRangeDouble, min: Double, max: Double, oldValue: Double, newValue: Double)
        fun onValueMax(observable: ObservableValueRangeDouble, max: Double) {}
        fun onValueMin(observable: ObservableValueRangeDouble, min: Double) {}
        fun onValueOver(observable: ObservableValueRangeDouble, over: Double) {}

        class Management : ObserverManagement<Observer>(), Observer {
            override fun onValueChange(observable: ObservableValueRangeDouble, min: Double, max: Double, oldValue: Double, newValue: Double) {
                for (observer in getIterationObservers())
                    observer.onValueChange(observable, min, max, oldValue, newValue)
            }
            override fun onValueMax(observable: ObservableValueRangeDouble, max: Double) {
                for (observer in getIterationObservers())
                    observer.onValueMax(observable, max)
            }
            override fun onValueMin(observable: ObservableValueRangeDouble, min: Double) {
                for (observer in getIterationObservers())
                    observer.onValueMin(observable, min)
            }
            override fun onValueOver(observable: ObservableValueRangeDouble, over: Double) {
                for (observer in getIterationObservers())
                    observer.onValueOver(observable, over)
            }
        }

    }

    fun addValueObserver(observer: Observer): Observing

}
interface ObservableValueRangeFloat : ValueRangeFloat {

    interface Observer {

        fun onValueChange(observable: ObservableValueRangeFloat, min: Float, max: Float, oldValue: Float, newValue: Float)
        fun onValueMax(observable: ObservableValueRangeFloat, max: Float) {}
        fun onValueMin(observable: ObservableValueRangeFloat, min: Float) {}
        fun onValueOver(observable: ObservableValueRangeFloat, over: Float) {}

        class Management : ObserverManagement<Observer>(), Observer {
            override fun onValueChange(observable: ObservableValueRangeFloat, min: Float, max: Float, oldValue: Float, newValue: Float) {
                for (observer in getIterationObservers())
                    observer.onValueChange(observable, min, max, oldValue, newValue)
            }
            override fun onValueMax(observable: ObservableValueRangeFloat, max: Float) {
                for (observer in getIterationObservers())
                    observer.onValueMax(observable, max)
            }
            override fun onValueMin(observable: ObservableValueRangeFloat, min: Float) {
                for (observer in getIterationObservers())
                    observer.onValueMin(observable, min)
            }
            override fun onValueOver(observable: ObservableValueRangeFloat, over: Float) {
                for (observer in getIterationObservers())
                    observer.onValueOver(observable, over)
            }
        }

    }

    fun addValueObserver(observer: Observer): Observing

}
interface ObservableValueRangeLong : ValueRangeLong {

    interface Observer {

        fun onValueChange(observable: ObservableValueRangeLong, min: Long, max: Long, oldValue: Long, newValue: Long)
        fun onValueMax(observable: ObservableValueRangeLong, max: Long) {}
        fun onValueMin(observable: ObservableValueRangeLong, min: Long) {}
        fun onValueOver(observable: ObservableValueRangeLong, over: Long) {}

        class Management : ObserverManagement<Observer>(), Observer {
            override fun onValueChange(observable: ObservableValueRangeLong, min: Long, max: Long, oldValue: Long, newValue: Long) {
                for (observer in getIterationObservers())
                    observer.onValueChange(observable, min, max, oldValue, newValue)
            }
            override fun onValueMax(observable: ObservableValueRangeLong, max: Long) {
                for (observer in getIterationObservers())
                    observer.onValueMax(observable, max)
            }
            override fun onValueMin(observable: ObservableValueRangeLong, min: Long) {
                for (observer in getIterationObservers())
                    observer.onValueMin(observable, min)
            }
            override fun onValueOver(observable: ObservableValueRangeLong, over: Long) {
                for (observer in getIterationObservers())
                    observer.onValueOver(observable, over)
            }
        }

    }

    fun addValueObserver(observer: Observer): Observing

}
interface ObservableValueRangeInt : ValueRangeInt {

    interface Observer {

        fun onValueChange(observable: ObservableValueRangeInt, min: Int, max: Int, oldValue: Int, newValue: Int)
        fun onValueMax(observable: ObservableValueRangeInt, max: Int) {}
        fun onValueMin(observable: ObservableValueRangeInt, min: Int) {}
        fun onValueOver(observable: ObservableValueRangeInt, over: Int) {}

        class Management : ObserverManagement<Observer>(), Observer {
            override fun onValueChange(observable: ObservableValueRangeInt, min: Int, max: Int, oldValue: Int, newValue: Int) {
                for (observer in getIterationObservers())
                    observer.onValueChange(observable, min, max, oldValue, newValue)
            }
            override fun onValueMax(observable: ObservableValueRangeInt, max: Int) {
                for (observer in getIterationObservers())
                    observer.onValueMax(observable, max)
            }
            override fun onValueMin(observable: ObservableValueRangeInt, min: Int) {
                for (observer in getIterationObservers())
                    observer.onValueMin(observable, min)
            }
            override fun onValueOver(observable: ObservableValueRangeInt, over: Int) {
                for (observer in getIterationObservers())
                    observer.onValueOver(observable, over)
            }
        }

    }

    fun addValueObserver(observer: Observer): Observing

}

open class ValueRangeDoubleImpl(private val value: Double,
                                private val min: Double,
                                private val max: Double) : ObservableValueRangeDouble, ObservableFraction {

    private var valueObserver: ObservableValueRangeDouble.Observer.Management = ObservableValueRangeDouble.Observer.Management()
    private var fractionObserver: ObservableFraction.Manager = ObservableFraction.Manager()

    override fun addFractionObserver(observer: FractionObserver): Observing {
        return fractionObserver.addObserver(observer)
    }

    override fun addValueObserver(observer: ObservableValueRangeDouble.Observer): Observing {
        return valueObserver.addObserver(observer)
    }

    override fun getMax(): Double {
        return max
    }
    override fun getMin(): Double {
        return min
    }
    override fun getValue(): Double {
        return value
    }

}
open class ValueRangeFloatImpl(
        private val value: Float,
        private val min: Float,
        private val max: Float) : ObservableValueRangeFloat, ObservableFraction {

    private var valueObserver: ObservableValueRangeFloat.Observer.Management = ObservableValueRangeFloat.Observer.Management()
    private var fractionObserver: ObservableFraction.Manager = ObservableFraction.Manager()

    override fun addFractionObserver(observer: FractionObserver): Observing {
        return fractionObserver.addObserver(observer)
    }

    override fun addValueObserver(observer: ObservableValueRangeFloat.Observer): Observing {
        return valueObserver.addObserver(observer)
    }

    override fun getMax(): Float {
        return max
    }
    override fun getMin(): Float {
        return min
    }
    override fun getValue(): Float {
        return value
    }

}
open class ValueRangeLongImpl(
        private val value: Long,
        private val min: Long,
        private val max: Long) : ObservableValueRangeLong, ObservableFraction {

    private var valueObserver: ObservableValueRangeLong.Observer.Management = ObservableValueRangeLong.Observer.Management()
    private var fractionObserver: ObservableFraction.Manager = ObservableFraction.Manager()

    override fun addFractionObserver(observer: FractionObserver): Observing {
        return fractionObserver.addObserver(observer)
    }

    override fun addValueObserver(observer: ObservableValueRangeLong.Observer): Observing {
        return valueObserver.addObserver(observer)
    }

    override fun getMax(): Long {
        return max
    }
    override fun getMin(): Long {
        return min
    }
    override fun getValue(): Long {
        return value
    }

}
open class ValueRangeIntImpl(
        private val value: Int,
        private val min: Int,
        private val max: Int) : ObservableValueRangeInt, ObservableFraction {

    private var valueObserver: ObservableValueRangeInt.Observer.Management = ObservableValueRangeInt.Observer.Management()
    private var fractionObserver: ObservableFraction.Manager = ObservableFraction.Manager()

    override fun addFractionObserver(observer: FractionObserver): Observing {
        return fractionObserver.addObserver(observer)
    }

    override fun addValueObserver(observer: ObservableValueRangeInt.Observer): Observing {
        return valueObserver.addObserver(observer)
    }

    override fun getMax(): Int {
        return max
    }
    override fun getMin(): Int {
        return min
    }
    override fun getValue(): Int {
        return value
    }

}

open class MutableValueRangeDoubleImpl(
        /** Текущее значение  */
        private var value: Double,
        /** Минимальное значение  */
        private var min: Double,
        /** Максимальное значение  */
        private var max: Double) : ValueRangeDouble.Mutable, ObservableValueRangeDouble, ObservableFraction {

    private var valueObserver: ObservableValueRangeDouble.Observer.Management = ObservableValueRangeDouble.Observer.Management()
    private var fractionObserver: ObservableFraction.Manager = ObservableFraction.Manager()

    override fun addFractionObserver(observer: FractionObserver): Observing {
        return fractionObserver.addObserver(observer)
    }

    override fun addValueObserver(observer: ObservableValueRangeDouble.Observer): Observing {
        return valueObserver.addObserver(observer)
    }

    protected open fun onValueChange(oldValue: Double, newValue: Double) {}
    protected open fun onValueMax(value: Double) {}
    protected open fun onValueMin(value: Double) {}
    protected open fun onValueOver(over: Double) {}

    override fun getValue(): Double {
        return value
    }
    override fun setValue(value: Double) {
        if (getValue() == value) return
        val old = getValue()
        this.value = value
        if (getValue() > getMax()) {
            val over = getValue() - getMax()
            onValueOver(over)
            valueObserver.onValueOver(this, over)
            this.value = getMax()
        }
        if (getValue() < getMin()) {
            val over = getMin() - getValue()
            onValueOver(over)
            valueObserver.onValueOver(this, over)
            this.value = getMin()
        }
        onValueChange(old, getValue())
        valueObserver.onValueChange(this, getMin(), getMax(), old, getValue())
        fractionObserver.onFractionChange(this, (old / getRange()), getFraction())
        if (getValue() == getMax()) {
            onValueMax(getValue())
            valueObserver.onValueMax(this, getMax())
        }
        if (getValue() == getMin()) {
            onValueMin(getValue())
            valueObserver.onValueMin(this, getMin())
        }
    }

    override fun getMax(): Double {
        return max
    }
    override fun setMax(max: Double) {
        this.max = max
    }

    override fun getMin(): Double {
        return min
    }
    override fun setMin(min: Double) {
        this.min = min
    }

}
open class MutableValueRangeFloatImpl(
        /** Текущее значение  */
        private var value: Float,
        /** Минимальное значение  */
        private var min: Float,
        /** Максимальное значение  */
        private var max: Float) : ValueRangeFloat.Mutable, ObservableValueRangeFloat, ObservableFraction {

    private var valueObserver: ObservableValueRangeFloat.Observer.Management = ObservableValueRangeFloat.Observer.Management()
    private var fractionObserver: ObservableFraction.Manager = ObservableFraction.Manager()

    override fun addFractionObserver(observer: FractionObserver): Observing {
        return fractionObserver.addObserver(observer)
    }

    override fun addValueObserver(observer: ObservableValueRangeFloat.Observer): Observing {
        return valueObserver.addObserver(observer)
    }

    protected open fun onValueChange(oldValue: Float, newValue: Float) {}
    protected open fun onValueMax(value: Float) {}
    protected open fun onValueMin(value: Float) {}
    protected open fun onValueOver(over: Float) {}

    override fun getValue(): Float {
        return value
    }
    override fun setValue(value: Float) {
        if (getValue() == value) return
        val old = getValue()
        this.value = value
        if (getValue() > getMax()) {
            val over = getValue() - getMax()
            onValueOver(over)
            valueObserver.onValueOver(this, over)
            this.value = getMax()
        }
        if (getValue() < getMin()) {
            val over = getMin() - getValue()
            onValueOver(over)
            valueObserver.onValueOver(this, over)
            this.value = getMin()
        }
        onValueChange(old, getValue())
        valueObserver.onValueChange(this, getMin(), getMax(), old, getValue())
        fractionObserver.onFractionChange(this, (old.toDouble() / getRange().toDouble()), getFraction())
        if (getValue() == getMax()) {
            onValueMax(getValue())
            valueObserver.onValueMax(this, getMax())
        }
        if (getValue() == getMin()) {
            onValueMin(getValue())
            valueObserver.onValueMin(this, getMin())
        }
    }

    override fun getMax(): Float {
        return max
    }
    override fun setMax(max: Float) {
        this.max = max
    }

    override fun getMin(): Float {
        return min
    }
    override fun setMin(min: Float) {
        this.min = min
    }

}
open class MutableValueRangeLongImpl(
        /** Текущее значение  */
        private var value: Long,
        /** Минимальное значение  */
        private var min: Long,
        /** Максимальное значение  */
        private var max: Long) : ValueRangeLong.Mutable, ObservableValueRangeLong, ObservableFraction {

    private var valueObserver: ObservableValueRangeLong.Observer.Management = ObservableValueRangeLong.Observer.Management()
    private var fractionObserver: ObservableFraction.Manager = ObservableFraction.Manager()

    override fun addFractionObserver(observer: FractionObserver): Observing {
        return fractionObserver.addObserver(observer)
    }

    override fun addValueObserver(observer: ObservableValueRangeLong.Observer): Observing {
        return valueObserver.addObserver(observer)
    }

    protected open fun onValueChange(oldValue: Long, newValue: Long) {}
    protected open fun onValueMax(value: Long) {}
    protected open fun onValueMin(value: Long) {}
    protected open fun onValueOver(over: Long) {}

    override fun getValue(): Long {
        return value
    }
    override fun setValue(value: Long) {
        if (getValue() == value) return
        val old = getValue()
        this.value = value
        if (getValue() > getMax()) {
            val over = getValue() - getMax()
            onValueOver(over)
            valueObserver.onValueOver(this, over)
            this.value = getMax()
        }
        if (getValue() < getMin()) {
            val over = getMin() - getValue()
            onValueOver(over)
            valueObserver.onValueOver(this, over)
            this.value = getMin()
        }
        onValueChange(old, getValue())
        valueObserver.onValueChange(this, getMin(), getMax(), old, getValue())
        fractionObserver.onFractionChange(this, (old.toDouble() / getRange().toDouble()), getFraction())
        if (getValue() == getMax()) {
            onValueMax(getValue())
            valueObserver.onValueMax(this, getMax())
        }
        if (getValue() == getMin()) {
            onValueMin(getValue())
            valueObserver.onValueMin(this, getMin())
        }
    }

    override fun getMax(): Long {
        return max
    }
    override fun setMax(max: Long) {
        this.max = max
    }

    override fun getMin(): Long {
        return min
    }
    override fun setMin(min: Long) {
        this.min = min
    }

}
open class MutableValueRangeIntImpl(
        /** Текущее значение  */
        private var value: Int,
        /** Минимальное значение  */
        private var min: Int,
        /** Максимальное значение  */
        private var max: Int) : ValueRangeInt.Mutable, ObservableValueRangeInt, ObservableFraction {

    private var valueObserver: ObservableValueRangeInt.Observer.Management = ObservableValueRangeInt.Observer.Management()
    private var fractionObserver: ObservableFraction.Manager = ObservableFraction.Manager()

    override fun addFractionObserver(observer: FractionObserver): Observing {
        return fractionObserver.addObserver(observer)
    }

    override fun addValueObserver(observer: ObservableValueRangeInt.Observer): Observing {
        return valueObserver.addObserver(observer)
    }

    protected open fun onValueChange(oldValue: Int, newValue: Int) {}
    protected open fun onValueMax(value: Int) {}
    protected open fun onValueMin(value: Int) {}
    protected open fun onValueOver(over: Int) {}

    override fun getValue(): Int {
        return value
    }
    override fun setValue(value: Int) {
        if (getValue() == value) return
        val old = getValue()
        this.value = value
        if (getValue() > getMax()) {
            val over = getValue() - getMax()
            onValueOver(over)
            valueObserver.onValueOver(this, over)
            this.value = getMax()
        }
        if (getValue() < getMin()) {
            val over = getMin() - getValue()
            onValueOver(over)
            valueObserver.onValueOver(this, over)
            this.value = getMin()
        }
        onValueChange(old, getValue())
        valueObserver.onValueChange(this, getMin(), getMax(), old, getValue())
        fractionObserver.onFractionChange(this, (old.toDouble() / getRange().toDouble()), getFraction())
        if (getValue() == getMax()) {
            onValueMax(getValue())
            valueObserver.onValueMax(this, getMax())
        }
        if (getValue() == getMin()) {
            onValueMin(getValue())
            valueObserver.onValueMin(this, getMin())
        }
    }

    override fun getMax(): Int {
        return max
    }
    override fun setMax(max: Int) {
        this.max = max
    }

    override fun getMin(): Int {
        return min
    }
    override fun setMin(min: Int) {
        this.min = min
    }

}

fun ValueRangeDouble.contain(value: Double) = getValue() - value >= getMin()
fun ValueRangeFloat.contain(value: Float) = getValue() - value >= getMin()
fun ValueRangeLong.contain(value: Long) = getValue() - value >= getMin()
fun ValueRangeInt.contain(value: Int) = getValue() - value >= getMin()

