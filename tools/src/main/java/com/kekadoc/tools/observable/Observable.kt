package com.kekadoc.tools.observable

interface Observable<T> {

    fun getValue(): T

    fun observe(observer: Observer<T>): Observing
    fun removeObserver(observer: Observer<T>?): Boolean

}