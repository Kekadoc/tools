package com.kekadoc.tools.value

import com.kekadoc.tools.fraction.Fraction
import com.kekadoc.tools.fraction.FractionObserver
import com.kekadoc.tools.fraction.ObservableFraction
import com.kekadoc.tools.observable.ObservationManager
import com.kekadoc.tools.observable.Observing

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

    interface Observable : Mutable {

        interface Observer {

            fun onValueChange(observable: Observable, min: Double, max: Double, oldValue: Double, newValue: Double)
            fun onValueMax(observable: Observable, max: Double) {}
            fun onValueMin(observable: Observable, min: Double) {}
            fun onValueOver(observable: Observable, over: Double) {}

            class Manager : ObservationManager<Observer>(), Observer {
                override fun onValueChange(observable: Observable, min: Double, max: Double, oldValue: Double, newValue: Double) {
                    for (observer in getIterationObservers())
                        observer.onValueChange(observable, min, max, oldValue, newValue)
                }
                override fun onValueMax(observable: Observable, max: Double) {
                    for (observer in getIterationObservers())
                        observer.onValueMax(observable, max)
                }
                override fun onValueMin(observable: Observable, min: Double) {
                    for (observer in getIterationObservers())
                        observer.onValueMin(observable, min)
                }
                override fun onValueOver(observable: Observable, over: Double) {
                    for (observer in getIterationObservers())
                        observer.onValueOver(observable, over)
                }
            }

        }

        fun addValueObserver(observer: Observer): Observing

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

    interface Observable : Mutable {

        interface Observer {

            fun onValueChange(observable: Observable, min: Float, max: Float, oldValue: Float, newValue: Float)
            fun onValueMax(observable: Observable, max: Float) {}
            fun onValueMin(observable: Observable, min: Float) {}
            fun onValueOver(observable: Observable, over: Float) {}

            class Manager : ObservationManager<Observer>(), Observer {
                override fun onValueChange(observable: Observable, min: Float, max: Float, oldValue: Float, newValue: Float) {
                    for (observer in getIterationObservers())
                        observer.onValueChange(observable, min, max, oldValue, newValue)
                }
                override fun onValueMax(observable: Observable, max: Float) {
                    for (observer in getIterationObservers())
                        observer.onValueMax(observable, max)
                }
                override fun onValueMin(observable: Observable, min: Float) {
                    for (observer in getIterationObservers())
                        observer.onValueMin(observable, min)
                }
                override fun onValueOver(observable: Observable, over: Float) {
                    for (observer in getIterationObservers())
                        observer.onValueOver(observable, over)
                }
            }

        }

        fun addValueObserver(observer: Observer): Observing

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

    interface Observable : Mutable {

        interface Observer {

            fun onValueChange(observable: Observable, min: Long, max: Long, oldValue: Long, newValue: Long)
            fun onValueMax(observable: Observable, max: Long) {}
            fun onValueMin(observable: Observable, min: Long) {}
            fun onValueOver(observable: Observable, over: Long) {}

            class Manager : ObservationManager<Observer>(), Observer {
                override fun onValueChange(observable: Observable, min: Long, max: Long, oldValue: Long, newValue: Long) {
                    for (observer in getIterationObservers())
                        observer.onValueChange(observable, min, max, oldValue, newValue)
                }
                override fun onValueMax(observable: Observable, max: Long) {
                    for (observer in getIterationObservers())
                        observer.onValueMax(observable, max)
                }
                override fun onValueMin(observable: Observable, min: Long) {
                    for (observer in getIterationObservers())
                        observer.onValueMin(observable, min)
                }
                override fun onValueOver(observable: Observable, over: Long) {
                    for (observer in getIterationObservers())
                        observer.onValueOver(observable, over)
                }
            }

        }

        fun addValueObserver(observer: Observer): Observing

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

    interface Observable : Mutable {

        interface Observer {

            fun onValueChange(observable: Observable, min: Int, max: Int, oldValue: Int, newValue: Int)
            fun onValueMax(observable: Observable, max: Int) {}
            fun onValueMin(observable: Observable, min: Int) {}
            fun onValueOver(observable: Observable, over: Int) {}

            class Manager : ObservationManager<Observer>(), Observer {
                override fun onValueChange(observable: Observable, min: Int, max: Int, oldValue: Int, newValue: Int) {
                    for (observer in getIterationObservers())
                        observer.onValueChange(observable, min, max, oldValue, newValue)
                }
                override fun onValueMax(observable: Observable, max: Int) {
                    for (observer in getIterationObservers())
                        observer.onValueMax(observable, max)
                }
                override fun onValueMin(observable: Observable, min: Int) {
                    for (observer in getIterationObservers())
                        observer.onValueMin(observable, min)
                }
                override fun onValueOver(observable: Observable, over: Int) {
                    for (observer in getIterationObservers())
                        observer.onValueOver(observable, over)
                }
            }

        }

        fun addValueObserver(observer: Observer): Observing

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


open class ValueRangeDoubleImpl(private var value: Double,
                                private var min: Double,
                                private var max: Double) : ValueRangeDouble.Observable, ObservableFraction {

    private var valueObserver = ValueRangeDouble.Observable.Observer.Manager()
    private var fractionObserver: ObservableFraction.Manager = ObservableFraction.Manager()

    override fun addFractionObserver(observer: FractionObserver): Observing {
        return fractionObserver.addObserver(observer)
    }
    override fun addValueObserver(observer: ValueRangeDouble.Observable.Observer): Observing {
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

    override fun setMax(max: Double) {
        this.max = max
    }
    override fun setMin(min: Double) {
        this.min = min
    }
    override fun setValue(value: Double) {
        this.value = value
    }

    override fun setFraction(fraction: Double) {
        setValue(getRange() * fraction)
    }

}
open class ValueRangeFloatImpl(private var value: Float,
                               private var min: Float,
                               private var max: Float) : ValueRangeFloat.Observable, ObservableFraction {

    private var valueObserver = ValueRangeFloat.Observable.Observer.Manager()
    private var fractionObserver: ObservableFraction.Manager = ObservableFraction.Manager()

    override fun addFractionObserver(observer: FractionObserver): Observing {
        return fractionObserver.addObserver(observer)
    }
    override fun addValueObserver(observer: ValueRangeFloat.Observable.Observer): Observing {
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

    override fun setMax(max: Float) {
        this.max = max
    }
    override fun setMin(min: Float) {
        this.min = min
    }
    override fun setValue(value: Float) {
        this.value = value
    }

    override fun setFraction(fraction: Double) {
        setValue((getRange() * fraction).toFloat())
    }

}
open class ValueRangeLongImpl(private var value: Long,
                               private var min: Long,
                               private var max: Long) : ValueRangeLong.Observable, ObservableFraction {

    private var valueObserver = ValueRangeLong.Observable.Observer.Manager()
    private var fractionObserver: ObservableFraction.Manager = ObservableFraction.Manager()

    override fun addFractionObserver(observer: FractionObserver): Observing {
        return fractionObserver.addObserver(observer)
    }
    override fun addValueObserver(observer: ValueRangeLong.Observable.Observer): Observing {
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

    override fun setMax(max: Long) {
        this.max = max
    }
    override fun setMin(min: Long) {
        this.min = min
    }
    override fun setValue(value: Long) {
        this.value = value
    }

    override fun setFraction(fraction: Double) {
        setValue((getRange() * fraction).toLong())
    }

}
open class ValueRangeIntImpl(private var value: Int,
                               private var min: Int,
                               private var max: Int) : ValueRangeInt.Observable, ObservableFraction {

    private var valueObserver = ValueRangeInt.Observable.Observer.Manager()
    private var fractionObserver: ObservableFraction.Manager = ObservableFraction.Manager()

    override fun addFractionObserver(observer: FractionObserver): Observing {
        return fractionObserver.addObserver(observer)
    }
    override fun addValueObserver(observer: ValueRangeInt.Observable.Observer): Observing {
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

    override fun setMax(max: Int) {
        this.max = max
    }
    override fun setMin(min: Int) {
        this.min = min
    }
    override fun setValue(value: Int) {
        this.value = value
    }

    override fun setFraction(fraction: Double) {
        setValue((getRange() * fraction).toInt())
    }

}


fun ValueRangeDouble.contain(value: Double) = getValue() - value >= getMin()
fun ValueRangeFloat.contain(value: Float) = getValue() - value >= getMin()
fun ValueRangeLong.contain(value: Long) = getValue() - value >= getMin()
fun ValueRangeInt.contain(value: Int) = getValue() - value >= getMin()

