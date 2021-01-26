package com.qeapp.tools.fraction

import com.qeapp.tools.observer.ObserverManagement

interface FractionObserver {

    fun onFractionChange(fraction: ObservableFraction?, oldFraction: Double, newFraction: Double)

    class Management : ObserverManagement<FractionObserver?>(), FractionObserver {
        override fun onFractionChange(fraction: ObservableFraction?, oldFraction: Double, newFraction: Double) {
            getObservers()?.let {
                for (observer in it) observer!!.onFractionChange(fraction, oldFraction, newFraction)
            }
        }
    }

}