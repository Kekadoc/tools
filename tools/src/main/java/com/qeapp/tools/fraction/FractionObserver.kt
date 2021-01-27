package com.qeapp.tools.fraction

interface FractionObserver {

    fun onFractionChange(fraction: ObservableFraction, oldFraction: Double, newFraction: Double)

}