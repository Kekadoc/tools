package com.kekadoc.tools.data.state

import com.kekadoc.tools.observable.Observing

fun <Data, State, Keeper: StateKeeper<Data, State>>
        dataStatesCollector(keeper: DataStatesCollector<Data, State, Keeper>.() -> Keeper):
        DataStatesCollector<Data, State, Keeper> {
    return object : DataStatesCollector<Data, State, Keeper>() {
        override fun onCreateStateKeeper(data: Data, state: State): Keeper {
            return keeper.invoke(this)
        }
    }
}

fun <Data, State> dataStatesCollector():
        DataStatesCollector<Data, State, StateKeeper.Default<Data, State>> {
    return DataStatesCollector.Simple()
}

fun <Data, State> dataStatesCollector(observer: (keeper: StateKeeper<Data, State>, oldState: State, newState: State) -> Unit):
        DataStatesCollector<Data, State, StateKeeper.Default<Data, State>> {
    return object : DataStatesCollector.Simple<Data, State>() {
        override fun onDataStateChange(
            keeper: StateKeeper.Default<Data, State>,
            oldState: State,
            newState: State
        ) {
            observer.invoke(keeper, oldState, newState)
        }
    }
}


abstract class DataStatesCollector<Data, State, Keeper : StateKeeper<Data, State>> {

    protected fun Data.isState(state: State): Boolean {
        return getStateKeeper(this)?.state == state
    }

    private val states = hashMapOf<Data, Keeper>()

    fun setState(data: Data, state: State) {
        val keeper = getStateKeeper(data) ?: throw NullPointerException()
        keeper.state = state
    }

    fun getStateKeeper(data: Data): Keeper? = states[data]
    fun getAllData(): List<Data> = states.keys.toList()
    fun getAll(): Collection<Keeper> = states.values
    fun clear() {
        getAll().forEach { remove(it) }
    }
    fun add(data: Data, state: State): Keeper {
        var keeper = getStateKeeper(data)

        if (keeper == null) {
            keeper = onCreateStateKeeper(data, state)
            this.states[data] = keeper
            keeper.observe { oldState, newState ->
                onDataStateChange(keeper, oldState, newState)
            }
            onAdded(keeper)
        }
        else keeper.state = state

        return keeper
    }
    fun remove(data: Data): Keeper? {
        val keeper = this.states.remove(data)
        keeper?.let {
            onRemoved(keeper)
            it.clear()
        }
        return keeper
    }
    fun remove(keeper: Keeper) {
        remove(keeper.data)
    }

    protected open fun onAdded(keeper: Keeper) {}
    protected open fun onRemoved(keeper: Keeper) {}

    protected abstract fun onCreateStateKeeper(data: Data, state: State): Keeper

    protected open fun onDataStateChange(keeper: Keeper, oldState: State, newState: State) {}

    open class Simple<Data, State> : DataStatesCollector<Data, State, StateKeeper.Default<Data, State>>() {
        override fun onCreateStateKeeper(data: Data, state: State): StateKeeper.Default<Data, State> {
            return StateKeeper.Default(data, state)
        }
    }

}

interface StateKeeper<Data, State> {

    val data: Data
    var state: State
    fun observe(observer: (oldState: State, newState: State) -> Unit): Observing
    fun clear()

    open class Default<Data, State>(data: Data, protected val initState: State) : StateKeeper<Data, State> {

        override val data: Data = data
        override var state: State = initState
            set(value) {
                if (field == value) return
                val old = field
                field = value
                onStateChange(old, field)
                observers?.let {
                    it.forEach { observer -> observer.invoke(old, field) }
                }
            }

        private var observers: MutableSet<((oldState: State, newState: State) -> Unit)>? = null

        protected open fun onStateChange(oldState: State, newState: State) {}

        override fun observe(observer: (oldState: State, newState: State) -> Unit): Observing {
            if (observers == null) observers = mutableSetOf()
            observers!!.add(observer)
            observer.invoke(state, state)
            return object : Observing {
                override fun remove() {
                    observers?.remove(observer)
                }
            }
        }

        override fun clear() {
            state = initState
            observers?.clear()
        }

        override fun toString(): String {
            return "Default(data=$data, state=$state)"
        }


    }

}
