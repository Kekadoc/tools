package com.kekadoc.tools.observable

import com.kekadoc.tools.ifTrue
import com.kekadoc.tools.storage.collections.CollectionUtils
import java.util.*

abstract class ObservationManager<Observer> {

    companion object {
        protected const val REC_CAPACITY = 3
        protected fun <T> defaultCollection(): MutableCollection<T> {
            return LinkedHashSet(REC_CAPACITY)
        }
    }

    private var observers: MutableCollection<Observer>? = null

    open fun addObserver(observer: Observer): Observing {
        if (observers == null) observers = initialize()
        if (observers!!.contains(observer)) return Observing.EMPTY
        return if (!observers!!.add(observer)) Observing.EMPTY
        else {
            onObserverAttach(observer)
            onCreateObserving(observer)
        }
    }
    open fun removeObserver(observer: Observer?) {
        if (observer == null) return
        observers?.remove(observer)?.ifTrue { onObserverDetach(observer) }
    }

    fun size(): Int {
        return if (observers == null) 0 else observers!!.size
    }
    fun clear() {
        observers?.forEach { removeObserver(it) }
    }

    protected open fun onObserverAttach(observer: Observer) {}
    protected open fun onObserverDetach(observer: Observer) {}

    protected fun forEach(iterator: (it: Observer) -> Unit) {
        getIterationObservers().forEach { iterator.invoke(it) }
    }

    protected fun getObservers(): Collection<Observer>? {
        return observers
    }

    protected open fun onCreateObserving(observer: Observer): Observing {
        return object : Observing {
            override fun remove() {
                removeObserver(observer)
            }
        }
    }

    protected open fun getIterationObservers(): Collection<Observer> {
        return CollectionUtils.copy(observers)
    }

    protected open fun initialize(): MutableCollection<Observer> {
        return defaultCollection()
    }

}