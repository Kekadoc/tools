package com.kekadoc.tools.fraction

import com.kekadoc.tools.observable.Observing

open class FractionObject(private var fraction: Double = 0.0) : ObservableFraction, Fraction.Mutable {

    private var observers = ObservableFraction.Manager()

    override fun setFraction(fraction: Double) {
        val old = this.fraction
        this.fraction = fraction
        observers.onFractionChange(this, old, fraction)
    }
    override fun getFraction(): Double {
        return fraction
    }

    override fun addFractionObserver(observer: FractionObserver): Observing {
        return observers.addObserver(observer)
    }

}