package com.kekadoc.tools.observer

import com.kekadoc.tools.storage.collections.CollectionUtils
import java.util.*

abstract class ObserverManagement<Observer> {

    companion object {
        protected const val REC_CAPACITY = 3
        protected fun <T> defaultCollection(): MutableCollection<T> {
            return LinkedHashSet(REC_CAPACITY)
        }
    }

    private var observers: MutableCollection<Observer>? = null

    open fun addObserver(observer: Observer): Observing {
        if (observers == null) observers = initialize()
        observers!!.add(observer)
        return onCreateObserving(observer)
    }
    open fun removeObserver(observer: Observer?) {
        observers?.remove(observer)
    }

    fun size(): Int {
        return if (observers == null) 0 else observers!!.size
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

    protected open fun getSafetyListeners(): Collection<Observer> {
        return CollectionUtils.nonNull(observers)
    }
    protected open fun initialize(): MutableCollection<Observer> {
        return defaultCollection()
    }

}