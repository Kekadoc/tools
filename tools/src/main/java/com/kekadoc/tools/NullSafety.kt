package com.kekadoc.tools

object NullSafety {

    @JvmStatic fun safetyValue(number: Int?, safe: Int): Int {
        return number ?: safe
    }
    @JvmStatic fun safetyValue(number: Float?, safe: Float): Float {
        return number ?: safe
    }
    @JvmStatic fun safetyValue(number: Long?, safe: Long): Long {
        return number ?: safe
    }
    @JvmStatic fun safetyValue(number: Double?, safe: Double): Double {
        return number ?: safe
    }
    @JvmStatic fun safetyValue(number: Byte?, safe: Byte): Byte {
        return number ?: safe
    }
    @JvmStatic fun safetyValue(number: Short?, safe: Short): Short {
        return number ?: safe
    }

}