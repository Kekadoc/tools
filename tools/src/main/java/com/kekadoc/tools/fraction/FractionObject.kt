package com.kekadoc.tools.fraction

import com.kekadoc.tools.observer.Observing

open class FractionObject(private var fraction: FractionValue = 0.0) : ObservableFraction, Fraction.Mutable {

    private var observers = ObservableFraction.Manager()

    override fun setFraction(fraction: FractionValue) {
        val old = this.fraction
        this.fraction = fraction
        observers.onFractionChange(this, old, fraction)
    }
    override fun getFraction(): FractionValue {
        return fraction
    }

    override fun addFractionObserver(observer: FractionObserver): Observing {
        return observers.addObserver(observer)
    }

}