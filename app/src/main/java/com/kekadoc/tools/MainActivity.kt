package com.kekadoc.tools

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.kekadoc.tools.builder.AbstractBuilder
import com.kekadoc.tools.data.DataAggregate
import com.kekadoc.tools.data.DataStatesCollector
import com.kekadoc.tools.value.ValueUtils

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG: String = "MainActivity-TAG"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val states = States()
        val data_0 = "0";

        states.addData(data_0)
        states.addData("1")
        states.addData("2")

        Log.e(TAG, "onCreate: ${States.StringState::class.isInstance(states.getDataState(data_0))}")
        Log.e(TAG, "onCreate: ", )

        states.foreachState(States.StringState::class) {
            Log.e(TAG, "onCreate: $it")
        }

        val str: String = "str"
        Log.e(TAG, "onCreate: ${Int::class.isInstance(str)}")

        val q = ObjectUtils.requireNonNull(1)

        val from = 0L
        val to = 100L
        val current = 90L

        val events = object : ValueUtils.RangeChangeEvents<Long> {
            override fun onChange(min: Long, max: Long, current: Long, change: Long) {
                Log.e(TAG, "onChange: min = $min; max = $max; current = $current; change = $change")
            }
            override fun onOverflow(min: Long, max: Long, current: Long, overflow: Long) {
                Log.e(TAG, "onOverflow: min = $min; max = $max; current = $current; overflow = $overflow")
            }
            override fun onMax(min: Long, max: Long) {
                Log.e(TAG, "onMax: min = $min; max = $max")
            }
            override fun onMin(min: Long, max: Long) {
                Log.e(TAG, "onMin: min = $min; max = $max")
            }
        }

        ValueUtils.addValueInRange(from, to, current, 20, events)

        val dataAggregate = object : DataAggregate() {
            override fun createData(index: Int) {
                Log.e(TAG, "createData: $index")
            }
            override fun removeData(index: Int) {
                Log.e(TAG, "removeData: $index")
            }
        }
        dataAggregate.notifyData(5, 3)
    }

    private class States : DataStatesCollector<String, Int>() {

        override fun onDataAdded(data: String) {
            super.onDataAdded(data)
            foreachState(StringState::class) {
                Log.e(TAG, "onDataAdded: $it")
            }
        }

        override fun getDefaultState(): Int = 0
        override fun getNullState(): Int = -1

        override fun getDataState(data: String): StringState? {
            return super.getDataState(data) as StringState?
        }

        override fun onCreateStateHolder(data: String): StateHolder {
            return StringState(data)
        }

        inner class StringState(data: String) : StateHolder(data)

    }

    class Builder : AbstractBuilder<String?, Int?>() {

        override fun onCreateResult(param: Int?): String? {
            return param?.toString()
        }
        override fun onCreateParams(): Int {
            return 10;
        }

    }

}