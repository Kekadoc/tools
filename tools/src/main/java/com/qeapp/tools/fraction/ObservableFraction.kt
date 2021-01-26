package com.qeapp.tools.fraction

import com.qeapp.tools.observer.ObserverManagement

interface ObservableFraction : Fraction {

    fun addFractionObserver(observer: FractionObserver)
    fun removeFractionObserver(observer: FractionObserver?)

    class Manager : ObserverManagement<FractionObserver>(), FractionObserver {
        override fun onFractionChange(fraction: ObservableFraction?, oldFraction: Double, newFraction: Double) {
            for (observer in getIterationObservers()) observer.onFractionChange(fraction, oldFraction, newFraction)
        }
    }

}