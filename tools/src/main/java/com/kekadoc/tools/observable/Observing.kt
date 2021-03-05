package com.kekadoc.tools.observable

interface Observing {

    companion object {

        @JvmStatic
        val EMPTY: Observing = object : Observing {
            override fun remove() {
                throw NotImplementedError()
            }
        }
        @JvmStatic
        fun Observing.isEmptyInstance(): Boolean = this === EMPTY

    }

    fun remove()

}