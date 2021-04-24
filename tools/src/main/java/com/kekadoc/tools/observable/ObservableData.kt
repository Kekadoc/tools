package com.kekadoc.tools.observable

open class ObservableData<T> (value: T): AbstractObservableData<T>(value) {

    companion object {

        @JvmStatic
        fun <T> T.toObservable(): ObservableData<T> = ObservableData(this)
        @JvmStatic
        fun <T> ObservableData<T>.observe(observable: ObservableData<T>) {
            observe(observer { observable.updateValue(it) })
        }

    }

    private var observers: MutableCollection<Observer<T>>? = null


    override fun isActive(): Boolean {
        return observers != null && observers!!.isNotEmpty()
    }

    fun removeAllObservers(): Int {
        val size = this.observers?.size ?: 0
        this.observers?.clear()
        if (size > 0) onInactive()
        return size
    }

    override fun onChangeValue(oldValue: T, newValue: T) {
        observers?.onEach { it.onChange(this, oldValue, newValue) }
    }

    override fun observe(observer: Observer<T>): ObservingData<T> {
        if (observers == null) observers = hashSetOf()
        val active = isActive()
        observers!!.add(observer)
        if (!active && isActive()) onActive()
        observer.onAttach(this)
        observer.onChange(this, getValue(), getValue())
        return object : ObservingImpl() {
            override fun remove() {
                removeObserver(observer)
            }
        }
    }
    override fun removeObserver(observer: Observer<T>?): Boolean {
        val a = isActive()
        val removed = observers?.remove(observer)
        if (a && !isActive()) {
            onInactive()
            observer?.onDetach(this)
        }
        return removed ?: false
    }

}