package com.kekadoc.tools

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.kekadoc.tools.data.state.StateKeeper
import com.kekadoc.tools.data.state.dataStatesCollector

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    companion object {
        private const val TAG: String = "MainActivity-TAG"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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