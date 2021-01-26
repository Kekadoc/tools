package com.qeapp.tools.fraction

interface Fraction {

    fun getFraction() : Double

    interface Mutable : Fraction {
        fun setFraction(fraction: Double)
    }

}