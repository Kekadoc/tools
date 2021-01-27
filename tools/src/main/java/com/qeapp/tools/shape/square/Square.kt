package com.qeapp.tools.shape.square

import java.lang.IndexOutOfBoundsException

object Square {

    const val SIDE_INDEX_LEFT = 0
    const val SIDE_INDEX_TOP = 1
    const val SIDE_INDEX_RIGHT = 2
    const val SIDE_INDEX_BOTTOM = 3

    enum class Side(private val index: Int) {
        LEFT(SIDE_INDEX_LEFT),
        TOP(SIDE_INDEX_TOP),
        RIGHT(SIDE_INDEX_RIGHT),
        BOTTOM(SIDE_INDEX_BOTTOM);

        fun getIndex(): Int = index

        companion object {
            @JvmStatic
            fun byIndex(index: Int): Side {
                return when(index) {
                    SIDE_INDEX_LEFT -> LEFT
                    SIDE_INDEX_TOP -> TOP
                    SIDE_INDEX_RIGHT -> RIGHT
                    SIDE_INDEX_BOTTOM -> BOTTOM
                    else -> throw IndexOutOfBoundsException()
                }
            }
        }

    }

}