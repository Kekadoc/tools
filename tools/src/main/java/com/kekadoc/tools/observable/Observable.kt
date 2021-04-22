package com.kekadoc.tools.observable

interface Observable<T> {

    fun getValue(): T

    fun addObserve(observer: Observer<T>): Observing
    fun removeObserver(observer: Observer<T>?): Boolean

}