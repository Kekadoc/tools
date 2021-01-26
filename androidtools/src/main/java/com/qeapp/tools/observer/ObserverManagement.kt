package com.qeapp.tools.observer

import com.qeapp.tools.storage.collections.QeCollections
import java.util.*

abstract class ObserverManagement<Observer> {

    companion object {
        protected const val REC_CAPACITY = 3
        protected fun <T> defaultCollection(): MutableCollection<T> {
            return LinkedHashSet(REC_CAPACITY)
        }
    }

    private var observers: MutableCollection<Observer>? = null

    fun size(): Int {
        return if (observers == null) 0 else observers!!.size
    }
    fun addObserver(observer: Observer) {
        if (observers == null) observers = initialize()
        observers!!.add(observer)
    }
    fun removeObserver(observer: Observer?) {
        observers?.remove(observer)
    }
    protected fun getObservers(): Collection<Observer>? {
        return observers
    }

    protected open fun getIterationObservers(): Collection<Observer> {
        return QeCollections.copy(observers)
    }

    protected open fun getSafetyListeners(): Collection<Observer> {
        return QeCollections.nonNull(observers)
    }
    protected open fun initialize(): MutableCollection<Observer> {
        return defaultCollection()
    }

}