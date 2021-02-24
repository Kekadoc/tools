package com.kekadoc.tools.fraction

interface FractionObserver {

    fun onFractionChange(fraction: Fraction, oldFraction: Double, newFraction: Double)

}