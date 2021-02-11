package com.kekadoc.tools.data

import com.kekadoc.tools.ObjectUtils

abstract class DataStatesCollector<Data, State> {

    companion object {
        private const val TAG: String = "DataStateCollector-TAG"
    }

    protected fun Data.isState(state: State): Boolean {
        return getDataState(this)?.getState() == state
    }

    private val data = hashMapOf<Data, StateHolder>()

    open fun getDataState(data: Data): StateHolder? {
        return findDataState(data)
    }

    protected fun findDataState(data: Data): StateHolder? {
        return this.data[data]
    }

    protected fun <T : StateHolder> foreachState(clazz: Class<T>, iterator: (state: T) -> Unit) {
        data.forEach {
            iterator.invoke(ObjectUtils.tryCast<T>(it.value)!!)
        }
    }

    fun addData(data: Data): StateHolder {
        var viewState = this.data[data]

        if (viewState == null) {
            viewState = onCreateStateHolder(data)
            this.data[data] = viewState
            onDataAdded(data)
        }

        return viewState
    }
    fun removeData(data: Data) {
        if (this.data.contains(data)) {
            onDataRemoved(data)
            this.data.remove(data)?.clear()

        }
    }

    fun getData(): List<Data> = data.keys.toList()

    protected open fun onDataAdded(data: Data) {}
    protected open fun onDataRemoved(data: Data) {}

    protected abstract fun getDefaultState(): State
    protected abstract fun getNullState(): State

    protected open fun onCreateStateHolder(data: Data): StateHolder {
        return StateHolder(data)
    }

    protected open fun onDataStateChange(stateHolder: StateHolder, oldState: State, newState: State) {}

    open inner class StateHolder(val data: Data) {

        private var state: State = getNullState()

        init {
            setState(getDefaultState())
        }

        protected open fun isCanChangeState(state: State): Boolean {
            return true
        }
        fun getState(): State = state
        fun setState(state: State): Boolean {
            if (this.state == state) return false
            if (isCanChangeState(state)) {
                val old = this.state
                this.state = state
                onDataStateChange(this, old, state)
                onStateChange(old, state)
                return true
            }
            return false
        }

        protected open fun onStateChange(oldState: State, newState: State) {}

        internal fun clear() {
            setState(getNullState())
        }

    }

}

