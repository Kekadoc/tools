package com.qeapp.tools.fraction

fun Fraction.inverse(): Double {
    return 1.0 - getFraction()
}
fun Fraction.isMax(): Boolean {
    return getFraction() >= 1.0
}
fun Fraction.isMin(): Boolean {
    return getFraction() <= 0.0
}

interface Fraction {

    fun getFraction() : Double

    interface Mutable : Fraction {
        fun setFraction(fraction: Double)
    }

}