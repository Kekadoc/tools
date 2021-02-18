package com.kekadoc.tools.character

object CharUtils {

    @JvmStatic val NUMBERS = charArrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9')

    const val INFINITY = '\u221E'
    const val DOLLAR = '\u0024'
    const val PERCENT = '\u0025'
    const val ELLIPSIS = '\u2026'

    @JvmStatic
    fun isNumber(c: Char): Boolean {
        for (number in NUMBERS) if (c == number) return true
        return false
    }

    @JvmStatic
    @JvmOverloads
    fun parseNumber(chars: CharSequence, start: Int = 0, end: Int = chars.length - 1): Int {
        if (end > chars.length) throw RuntimeException("length = " + chars.length + "; start = " + start + "; end = " + end)
        var c: Char
        var started = false
        val n = StringBuilder()
        for (i in start..end) {
            c = chars[i]
            if (isNumber(c)) {
                n.append(c)
                started = true
            } else {
                if (started) break
            }
        }
        return n.toString().toInt()
    }

    @JvmStatic
    fun isNext(sequence: CharSequence, pos: Int, next: CharSequence): Boolean {
        if (pos + next.length > sequence.length) return false
        val bef = sequence.subSequence(pos, pos + next.length)
        return bef == next
    }

}