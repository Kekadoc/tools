package com.kekadoc.tools.observable

inline fun <T> observer(crossinline action: (value: T) -> Unit): Observer<T> {
    return Observer { _, _, newValue -> action.invoke(newValue) }
}

inline fun <T> ObservableData<T>.observe(crossinline action: (value: T) -> Unit): Observing {
    return addObserve(observer(action))
}