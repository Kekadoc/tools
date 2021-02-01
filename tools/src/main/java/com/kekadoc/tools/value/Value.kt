package com.kekadoc.tools.value

import com.kekadoc.tools.observer.ObserverManagement
import com.kekadoc.tools.observer.Observing

interface ValueLong {

    interface Mutable : ValueLong {
        fun setValue(value: Long)
    }

    fun getValue(): Long

}
interface ValueInt {

    interface Mutable : ValueInt {
        fun setValue(value: Int)
    }

    fun getValue(): Int

}
interface ValueDouble {

    interface Mutable : ValueDouble {
        fun setValue(value: Double)
    }

    fun getValue(): Double

}
interface ValueFloat {

    interface Mutable : ValueFloat {
        fun setValue(value: Float)
    }

    fun getValue(): Float

}

interface ObservableValueDouble : ValueDouble {

    interface Observer {

        fun onValueChange(observable: ObservableValueDouble?, oldValue: Double, newValue: Double)

        class Management : ObserverManagement<Observer>(), Observer {
            override fun onValueChange(observable: ObservableValueDouble?, oldValue: Double, newValue: Double) {
                for (observer in getIterationObservers()) observer.onValueChange(observable, oldValue, newValue)
            }
        }

    }

    fun addValueObserver(observer: Observer): Observing

}
interface ObservableValueFloat : ValueFloat {

    interface Observer {

        fun onValueChange(observable: ObservableValueFloat?, oldValue: Float, newValue: Float)

        class Management : ObserverManagement<Observer>(), Observer {
            override fun onValueChange(observable: ObservableValueFloat?, oldValue: Float, newValue: Float) {
                for (observer in getIterationObservers()) observer.onValueChange(observable, oldValue, newValue)
            }
        }

    }

    fun addValueObserver(observer: Observer): Observing

}
interface ObservableValueLong : ValueLong {

    interface Observer {

        fun onValueChange(fraction: ObservableValueLong, oldValue: Long, newValue: Long)

        class Management : ObserverManagement<Observer>(), Observer {
            override fun onValueChange(fraction: ObservableValueLong, oldValue: Long, newValue: Long) {
                for (observer in getIterationObservers())
                    observer.onValueChange(fraction, oldValue, newValue)
            }
        }

    }

    fun addValueObserver(observer: Observer): Observing

}
interface ObservableValueInt : ValueInt {

    interface Observer {

        fun onValueChange(fraction: ObservableValueInt, oldValue: Int, newValue: Int)

        class Management : ObserverManagement<Observer>(), Observer {
            override fun onValueChange(fraction: ObservableValueInt, oldValue: Int, newValue: Int) {
                for (observer in getIterationObservers())
                    observer.onValueChange(fraction, oldValue, newValue)
            }
        }

    }

    fun addValueObserver(observer: Observer): Observing

}