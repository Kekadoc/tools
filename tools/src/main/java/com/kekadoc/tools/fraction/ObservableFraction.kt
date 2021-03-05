package com.kekadoc.tools.fraction

import com.kekadoc.tools.observable.ObservationManager
import com.kekadoc.tools.observable.Observing

interface ObservableFraction : Fraction.Mutable {

    fun addFractionObserver(observer: FractionObserver): Observing

    class Manager : ObservationManager<FractionObserver>(), FractionObserver {
        override fun onFractionChange(fraction: Fraction, oldFraction: Double, newFraction: Double) {
            for (observer in getIterationObservers()) observer.onFractionChange(fraction, oldFraction, newFraction)
        }
    }

}