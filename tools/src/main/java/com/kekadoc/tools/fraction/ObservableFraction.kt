package com.kekadoc.tools.fraction

import com.kekadoc.tools.observer.ObserverManagement
import com.kekadoc.tools.observer.Observing

interface ObservableFraction : Fraction.Mutable {

    fun addFractionObserver(observer: FractionObserver): Observing

    class Manager : ObserverManagement<FractionObserver>(), FractionObserver {
        override fun onFractionChange(fraction: Fraction, oldFraction: Double, newFraction: Double) {
            for (observer in getIterationObservers()) observer.onFractionChange(fraction, oldFraction, newFraction)
        }
    }

}