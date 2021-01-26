package com.qeapp.tools

object NullSafety {

    fun safetyValue(number: Int?, safe: Int): Int {
        return number ?: safe
    }
    fun safetyValue(number: Float?, safe: Float): Float {
        return number ?: safe
    }
    fun safetyValue(number: Long?, safe: Long): Long {
        return number ?: safe
    }
    fun safetyValue(number: Double?, safe: Double): Double {
        return number ?: safe
    }
    fun safetyValue(number: Byte?, safe: Byte): Byte {
        return number ?: safe
    }
    fun safetyValue(number: Short?, safe: Short): Short {
        return number ?: safe
    }

}