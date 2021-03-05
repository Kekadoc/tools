package com.kekadoc.tools.observable

open class ObservableData<T> (value: T): Observable<T> {

    companion object {

        @JvmStatic
        fun <T> T.toObservable(): ObservableData<T> = ObservableData(this)
        @JvmStatic
        fun <T> ObservableData<T>.observe(observable: ObservableData<T>) {
            observe(observer { observable.updateValue(it) })
        }

    }

    private var observers: MutableCollection<Observer<T>>? = null
    private var data: T = value
        set(data) {
            val old: T = field
            field = data
            onChange(old, data)
            observers?.forEach { it.onChange(this, old, data) }
        }

    internal open fun notifyValue() {
        this.data = this.data
    }
    internal open fun setValue(value: T): Boolean {
        if (this.data == value) return false
        data = value
        return true
    }
    internal open fun updateValue(value: T) {
        this.data = value
    }

    fun isActive(): Boolean {
        return observers != null && observers!!.isNotEmpty()
    }

    override fun getValue(): T {
        return data
    }
    override fun observe(observer: Observer<T>): ObservingData<T> {
        if (observers == null) observers = hashSetOf()
        val active = isActive()
        observers!!.add(observer)
        if (!active && isActive()) onActive()
        observer.onChange(this, this.data, this.data)
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
    override fun removeObserver(observer: Observer<T>?): Boolean {
        return observers?.remove(observer) ?: false
    }

    protected open fun onChange(oldValue: T, newValue: T) {}

    protected open fun onActive() {}
    protected open fun onInactive() {}

    private abstract inner class ObservingImpl : ObservingData<T> {
        override fun getValue(): T = data
    }

}