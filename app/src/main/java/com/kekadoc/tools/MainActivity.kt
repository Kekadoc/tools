package com.kekadoc.tools

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.kekadoc.tools.data.state.StateKeeper
import com.kekadoc.tools.data.state.dataStatesCollector
import com.kekadoc.tools.fraction.Fraction
import com.kekadoc.tools.fraction.FractionObserver

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    class Progress : Fraction.Mutable {

        private var progress: Double = 0.0

        override fun getFraction(): Double {
            return progress
        }
        override fun setFraction(fraction: Double) {
            progress = fraction
        }

    }

    companion object {
        private const val TAG: String = "MainActivity-TAG"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val progress = Progress()

        Fraction.change(progress, 0.5, object : FractionObserver {
            override fun onFractionChange(fraction: Fraction, oldFraction: Double, newFraction: Double) {
                Log.e(TAG, "onFractionChange: ${fraction.getFraction()} $oldFraction $newFraction")
            }
        })

        val dataBase = arrayListOf<Data>()
        for (i in 0..20) dataBase.add(Data("Data #$i"))

        val dataStates = dataStatesCollector<Data, Int> {keeper, oldState, newState ->
            Log.e(TAG, "onDataStateChange ${keeper.data} oldState: $oldState newState: $newState")
        }

        val stateKeepers = arrayListOf<StateKeeper<Data, Int>>()
        dataBase.forEach {
            stateKeepers.add(dataStates.add(it, 0))
        }

        stateKeepers.forEach {
            it.state = stateKeepers.indexOf(it)
        }

    }


}

private data class Data(val message: String)