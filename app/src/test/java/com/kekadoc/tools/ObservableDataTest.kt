package com.kekadoc.tools

import com.kekadoc.tools.observer.Mutable
import com.kekadoc.tools.observer.Mutable.Companion.toMutable
import com.kekadoc.tools.observer.Observable
import com.kekadoc.tools.observer.Observable.Companion.observe
import com.kekadoc.tools.observer.Observable.Companion.toObservable
import org.junit.Test

class ObservableDataTest {

    companion object {
        private const val TAG: String = "ObservableDataTest-TAG"
    }

    var expectedNewCode = -5
    var expectedOldCode = 5

    @Test
    fun case() {
        val data = "0"
        val observable = data.toObservable()
        val mutable = data.toMutable()
        mutable.observe { _, value -> println(value) }
        mutable.setValue("1")
        mutable.setValue("1")
        mutable.updateValue("1")
        mutable.notifyValue()
    }

    @Test
    fun events() {
        var onObservingActive = false
        var onObservingInactive = false

        val codeData = object : Mutable<Int>(expectedOldCode) {
            override fun onChange(oldValue: Int, newValue: Int) {
                println(oldValue)
                assert(oldValue == expectedOldCode)
                assert(newValue == expectedNewCode)
            }
            override fun onActive() {
                onObservingActive = true
            }
            override fun onInactive() {
                onObservingInactive = true
            }

        }

        codeData.observe {_,_ ->

        }.remove()

        codeData.setValue(expectedNewCode)

        assert(onObservingActive && onObservingInactive)
    }
    @Test
    fun observe() {
        val codeData = Mutable(expectedOldCode)
        val obs = codeData.observe {old, new ->
            assert(old == expectedOldCode) {
                "old: $old != new: $expectedOldCode"
            }
            assert(new == expectedOldCode) {
                "old: $new != new: $expectedOldCode"
            }
        }
        obs.remove()
        var first = true
        codeData.observe {old, new ->
            if (first) {
                first = false
                println("old: $old | new: $new")
                assert(old == expectedOldCode) {
                    "old: $old != expectedOldCode: $expectedOldCode"
                }
                assert(new == expectedOldCode) {
                    "new: $new != expectedOldCode: $expectedOldCode"
                }
            } else {
                println("old: $old | new: $new")
                assert(old == expectedOldCode) {
                    "old: $old != expectedOldCode: $expectedOldCode"
                }
                assert(new == expectedNewCode) {
                    "new: $new != expectedNewCode: $expectedNewCode"
                }
            }

        }
        codeData.setValue(expectedNewCode)
    }
    @Test
    fun updateValue() {
        var count = 0
        val codeData = object : Mutable<Int>(expectedOldCode) {
            override fun onChange(oldValue: Int, newValue: Int) {
                super.onChange(oldValue, newValue)
                count++
            }
        }
        codeData.updateValue(expectedNewCode)
        codeData.updateValue(expectedNewCode)
        codeData.updateValue(expectedNewCode)

        assert(count == 3)
    }
    @Test
    fun setValue() {
        var changed = false
        val codeData = object : Mutable<Int>(expectedOldCode) {
            override fun onChange(oldValue: Int, newValue: Int) {
                super.onChange(oldValue, newValue)
                assert(!changed)
                changed = true
            }
        }
        codeData.setValue(expectedNewCode)
        codeData.setValue(expectedNewCode)
    }
    @Test
    fun toObservable() {
        val data = "Data"
        val observable = data.toObservable()
        observable.observe {_, value ->
            assert(value == data)
        }
    }
    @Test
    fun observeObservable() {
        var called = false
        var secondCall = 0
        val observable = Mutable(5)
        val observableSecond = Mutable(0)
        observableSecond.observe {_, value ->
            secondCall = value
            called = true
        }
        observable.observe(observableSecond)
        observable.setValue(expectedNewCode)
        assert(called && secondCall == expectedNewCode)
    }

}