package com.qeapp.tools

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.qeapp.tools.value.QeValue

// TODO: 27.01.2021 Перенести ListDataProvider

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val q = com.qeapp.tools.QeObjects.requireNonNull(1)

    }
}