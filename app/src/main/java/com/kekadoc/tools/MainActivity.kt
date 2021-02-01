package com.kekadoc.tools

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.kekadoc.tools.value.ValueUtils

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG: String = "MainActivity-TAG"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

    }

}