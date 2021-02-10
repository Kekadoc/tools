package com.kekadoc.tools.observer

interface Observing {

    companion object {
        val EMPTY: Observing = object : Observing {
            override fun remove() {
                throw NotImplementedError()
            }
        }
    }

    fun remove()
}