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
    interface Observing<Data> {
        fun remove()
        fun getData(): Data?
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

    fun observe(observer: Observer<Data>): Observing<Data> {
        if (observers == null) observers = LinkedHashSet()
        observers!!.add(observer)
        return object : ObservingImpl() {
            override fun remove() {
                observers?.remove(observer)
            }
        }
    }

    protected open fun onChange(oldTarget: Data?, newTarget: Data?) {}

    private abstract inner class ObservingImpl : Observing<Data> {
        override fun getData(): Data? = data
    }

}