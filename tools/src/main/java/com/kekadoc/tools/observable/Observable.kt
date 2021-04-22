package com.kekadoc.tools.observable

interface Observable<T> {

    fun getValue(): T

    fun addObserver(observer: Observer<T>): Observing
    fun removeObserver(observer: Observer<T>?): Boolean

}