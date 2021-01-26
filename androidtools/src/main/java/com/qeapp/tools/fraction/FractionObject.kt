package com.qeapp.tools.fraction

import java.util.*

open class FractionObject(private var fraction: Double = 0.0) : ObservableFraction, Fraction.Mutable {

    private var observers: MutableCollection<FractionObserver>? = null

    override fun setFraction(fraction: Double) {
        val old = this.fraction
        this.fraction = fraction
        if (observers != null) for (observer in observers!!) observer.onFractionChange(this, old, fraction)
    }
    override fun getFraction(): Double {
        return fraction
    }

    override fun addFractionObserver(observer: FractionObserver) {
        if (observers == null) observers = LinkedHashSet()
        observers!!.add(Objects.requireNonNull(observer))
    }
    override fun removeFractionObserver(observer: FractionObserver?) {
        observers?.remove(observer)
    }

}