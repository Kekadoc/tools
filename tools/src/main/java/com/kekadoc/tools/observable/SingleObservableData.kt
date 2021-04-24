package com.kekadoc.tools.observable

open class SingleObservableData<T> (value: T): AbstractObservableData<T>(value) {

    companion object {

        @JvmStatic
        fun <T> T.toSingleObservable(): SingleObservableData<T> = SingleObservableData(this)
        @JvmStatic
        fun <T> SingleObservableData<T>.observe(observable: SingleObservableData<T>) {
            observe(observer { observable.updateValue(it) })
        }

    }

    private var observer: Observer<T>? = null

    override fun onChangeValue(oldValue: T, newValue: T) {
        observer?.onChange(this, oldValue, newValue)
    }

    override fun isActive(): Boolean {
        return observer != null
    }

    fun removeObserver(): Boolean {
        if (observer == null) return false
        observer!!.onDetach(this)
        observer = null
        onInactive()
        return true
    }

    override fun observe(observer: Observer<T>): ObservingData<T> {
        this.observer?.onDetach(this)
        val active = isActive()
        this.observer = observer
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
        if (this.observer == observer) return removeObserver()
        return false
    }

}