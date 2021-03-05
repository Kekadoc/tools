package com.kekadoc.tools.observable

interface Mutable<T> : Observable<T> {

    fun setValue(value: T): Boolean

}