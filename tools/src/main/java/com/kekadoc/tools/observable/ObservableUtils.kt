package com.kekadoc.tools.observable

inline fun <T> observer(crossinline action: (value: T) -> Unit): Observer<T> {
    return Observer { _, _, newValue -> action.invoke(newValue) }
}

inline fun <T> Observable<T>.onEach(crossinline action: (value: T) -> Unit): Observing {
    return observe(observer(action))
}