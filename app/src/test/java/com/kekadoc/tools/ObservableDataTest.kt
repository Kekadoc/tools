package com.kekadoc.tools

import com.kekadoc.tools.observer.ObservableData
import com.kekadoc.tools.observer.ObservableData.Companion.observe
import com.kekadoc.tools.observer.ObservableData.Companion.toObservableData
import org.junit.Test

class ObservableDataTest {

    companion object {
        private const val TAG: String = "ObservableDataTest-TAG"
    }

    var expectedNewCode = -5
    var expectedOldCode = 5

    @Test
    fun events() {
        var onObservingActive = false
        var onObservingInactive = false

        val codeData = object : ObservableData<Int>(expectedOldCode) {
            override fun onChange(oldData: Int?, newData: Int?) {
                println(oldData)
                assert(oldData == expectedOldCode)
                assert(newData == expectedNewCode)
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
        print("$onObservingActive $onObservingInactive")
        assert(onObservingActive && onObservingInactive)
    }
    @Test
    fun observe() {
        val codeData = ObservableData(expectedOldCode)
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
        val codeData = object : ObservableData<Int>(expectedOldCode) {
            override fun onChange(oldData: Int?, newData: Int?) {
                super.onChange(oldData, newData)
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
        val codeData = object : ObservableData<Int>(expectedOldCode) {
            override fun onChange(oldData: Int?, newData: Int?) {
                super.onChange(oldData, newData)
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
        val observable = data.toObservableData()
        observable.observe {_, value ->
            assert(value == data)
        }
    }
    @Test
    fun observeObservable() {
        var called = false
        var secondCall = 0
        val observable = ObservableData(5)
        val observableSecond = ObservableData(0)
        observableSecond.observe {_, value ->
            secondCall = value!!
            called = true
        }
        observable.observe(observableSecond)
        observable.setValue(expectedNewCode)
        assert(called && secondCall == expectedNewCode)
    }

}