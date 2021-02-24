package com.kekadoc.tools

import com.kekadoc.tools.fraction.Fraction
import com.kekadoc.tools.fraction.FractionObserver
import com.kekadoc.tools.fraction.change
import org.junit.Test

class FractionTest {

    companion object {
        private const val TAG: String = "FractionTest-TAG"
    }

    class Progress : Fraction.Mutable {

        private var progress: Double = 0.0

        override fun getFraction(): Double {
            return progress
        }
        override fun setFraction(fraction: Double) {
            progress = fraction
        }

    }

    @Test
    fun change() {
        val desired = 0.5
        val progress = MainActivity.Progress()
        val value = progress.change(desired, object : FractionObserver {
            override fun onFractionChange(fraction: Fraction, oldFraction: Double, newFraction: Double) {
                System.out.println("onFractionChange: ${fraction.getFraction()} $oldFraction $newFraction")
            }
        })
        assert(progress.getFraction() == desired && value == desired)
    }
}