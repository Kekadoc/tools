package com.kekadoc.tools.fraction

import com.kekadoc.tools.observer.ObservableData

open class FractionWrapper : ObservableData<ObservableFraction>() {

    private val observer: FractionObserver = object : FractionObserver {
        override fun onFractionChange(
            fraction: Fraction,
            oldFraction: Double,
            newFraction: Double) {

            this@FractionWrapper.onFractionChange(fraction, oldFraction, newFraction)
        }
    }
    private var observing: com.kekadoc.tools.observer.Observing? = null

    protected fun onFractionChange(fraction: Fraction?, oldFraction: Double, newFraction: Double) {

    }

    override fun onChange(oldTarget: ObservableFraction?, newTarget: ObservableFraction?) {
        observing?.remove()
        observing = newTarget?.addFractionObserver(observer)
    }

}