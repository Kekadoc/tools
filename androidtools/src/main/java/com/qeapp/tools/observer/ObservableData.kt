package com.qeapp.tools.observer

import java.util.*

abstract class ObservableData<Data> {

    companion object {
        @JvmStatic
        fun <Data> attachSuperData(data: ObservableData<Data>, superData: ObservableData<Data>) {
            superData.observe(object : Observer<Data> {
                override fun onChange(oldData: Data?, newData: Data?) {
                    data.data = newData
                }
            })
        }
    }

    interface Observer<Data> {
        fun onChange(oldData: Data?, newData: Data?)
    }

    private var observers: MutableCollection<Observer<Data>>? = null
    var data: Data? = null
        set(data) {
            if (this.data === data) return
            val old: Data? = field
            field = data
            onChange(old, data)
            if (observers != null) for (observer in observers!!) observer.onChange(old, data)
        }

    fun observe(observer: Observer<Data>) {
        if (observers == null) observers = LinkedHashSet()
        observers!!.add(observer)
    }
    fun removeObserve(observer: Observer<Data>?): Boolean {
        return if (observers == null) false else observers!!.remove(observer)
    }

    protected open fun onChange(oldTarget: Data?, newTarget: Data?) {}

}