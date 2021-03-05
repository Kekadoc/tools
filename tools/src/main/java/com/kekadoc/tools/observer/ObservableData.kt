package com.kekadoc.tools.observer

open class ObservableData<T> (data: T? = null) {

    companion object {

        fun <T> T.toObservableData(): ObservableData<T> = ObservableData(this)

        fun <T> ObservableData<T>.observe(observable: ObservableData<T>) {
            observe {_, value ->
                observable.updateValue(value)
            }
        }

    }

    fun interface Observer<V> {
        fun onChange(oldData: V?, newData: V?)
    }
    interface Observing<V>: com.kekadoc.tools.observer.Observing {
        override fun remove()
        fun getData(): V?
    }

    private var observers: MutableCollection<Observer<T>>? = null
    private var data: T? = data
        set(data) {
            val old: T? = field
            field = data
            onChange(old, data)
            observers?.forEach { it.onChange(old, data) }
        }

    fun setValue(value: T?) {
        if (this.data == value) return
        data = value
    }
    fun updateValue(value: T?) {
        this.data = value
    }
    fun notifyValue() {
        this.data = this.data
    }

    fun isActive(): Boolean {
        return observers != null && observers!!.isNotEmpty()
    }

    fun observe(observer: Observer<T>): Observing<T> {
        if (observers == null) observers = hashSetOf()
        val active = isActive()
        observers!!.add(observer)
        if (!active && isActive()) onActive()
        observer.onChange(this.data, this.data)
        return object : ObservingImpl() {
            override fun remove() {
                observers?.let {
                    val a = isActive()
                    it.remove(observer)
                    if (a && !isActive()) onInactive()
                }
            }
        }
    }

    fun removeObserver(observer: Observer<T>): Boolean {
        return observers?.remove(observer) ?: false
    }

    protected open fun onChange(oldData: T?, newData: T?) {}

    protected open fun onActive() {}
    protected open fun onInactive() {}

    private abstract inner class ObservingImpl : Observing<T> {
        override fun getData(): T? = data
    }

}