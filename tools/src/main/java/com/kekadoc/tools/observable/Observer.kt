package com.kekadoc.tools.observable

fun interface Observer<V> {

    fun onChange(observable: Observable<V>, oldValue: V, newValue: V)

    fun onAttach(observable: Observable<V>) {}
    fun onDetach(observable: Observable<V>) {}

}