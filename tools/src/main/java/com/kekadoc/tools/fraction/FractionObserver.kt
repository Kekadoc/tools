package com.kekadoc.tools.fraction

interface FractionObserver {

    fun onFractionChange(fraction: ObservableFraction, oldFraction: Double, newFraction: Double)

}