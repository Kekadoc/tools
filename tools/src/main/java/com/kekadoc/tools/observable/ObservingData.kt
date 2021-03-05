package com.kekadoc.tools.observable

interface ObservingData<T> : Observing {
    override fun remove()
    fun getValue(): T
}