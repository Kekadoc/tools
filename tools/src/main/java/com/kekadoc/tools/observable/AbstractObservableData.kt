package com.kekadoc.tools.observable

abstract class AbstractObservableData<T> (value: T): Observable<T> {

    companion object {

        @JvmStatic
        fun <T> AbstractObservableData<T>.observe(observable: AbstractObservableData<T>) {
            observe(observer { observable.updateValue(it) })
        }

    }

    private var data: T = value
        set(data) {
            val old: T = field
            field = data
            onChange(old, data)
            onChangeValue(old, data)
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

    internal abstract fun onChangeValue(oldValue: T, newValue: T)

    abstract fun isActive(): Boolean

    override fun getValue(): T = data

    abstract override fun observe(observer: Observer<T>): ObservingData<T>
    abstract override fun removeObserver(observer: Observer<T>?): Boolean

    protected open fun onChange(oldValue: T, newValue: T) {}

    protected open fun onActive() {}
    protected open fun onInactive() {}

    protected abstract inner class ObservingImpl : ObservingData<T> {
        override fun getValue(): T = data
    }

}