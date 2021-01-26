package com.qeapp.tools.text

object QeCharSequence {

    fun createSequence(c: Char, count: Int): CharSequence? {
        val builder = StringBuilder()
        for (i in 0 until count) {
            builder.append(c)
        }
        return builder.toString()
    }

    fun createSequence(str: String?, count: Int): CharSequence? {
        val builder = StringBuilder()
        for (i in 0 until count) {
            builder.append(str)
        }
        return builder.toString()
    }

    fun contains(first: CharSequence, find: CharSequence, ignoreCase: Boolean): Boolean {
        return first.contains(find, ignoreCase)
    }

}