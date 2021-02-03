package com.kekadoc.tools.shape.square

import java.lang.IndexOutOfBoundsException

/**
 * Square metadata
 */
object Square {

    const val INDEX_EDGE_LEFT = 0
    const val INDEX_CORNER_TOP_LEFT = 1
    const val INDEX_EDGE_TOP = 2
    const val INDEX_CORNER_TOP_RIGHT = 3
    const val INDEX_EDGE_RIGHT = 4
    const val INDEX_CORNER_BOTTOM_RIGHT = 5
    const val INDEX_EDGE_BOTTOM = 6
    const val INDEX_CORNER_BOTTOM_LEFT = 7

    enum class Edge(private val index: Int) {

        LEFT(INDEX_EDGE_LEFT),
        TOP(INDEX_EDGE_TOP),
        RIGHT(INDEX_EDGE_RIGHT),
        BOTTOM(INDEX_EDGE_BOTTOM);

        fun getIndex(): Int = index

        companion object {
            @JvmStatic
            fun byIndex(index: Int): Edge {
                return when(index) {
                    INDEX_EDGE_LEFT -> LEFT
                    INDEX_EDGE_TOP -> TOP
                    INDEX_EDGE_RIGHT -> RIGHT
                    INDEX_EDGE_BOTTOM -> BOTTOM
                    else -> throw IndexOutOfBoundsException()
                }
            }
        }

        fun isEdge(index: Int): Boolean {
            return index in intArrayOf(INDEX_EDGE_LEFT, INDEX_EDGE_TOP, INDEX_EDGE_RIGHT, INDEX_EDGE_BOTTOM)
        }

        fun before(index: Int): Edge {
            return when(index) {
                INDEX_EDGE_LEFT -> BOTTOM
                INDEX_EDGE_TOP -> LEFT
                INDEX_EDGE_RIGHT -> TOP
                INDEX_EDGE_BOTTOM -> RIGHT
                else -> TOP
            }
        }
        fun next(index: Int): Edge {
            return when(index) {
                INDEX_EDGE_LEFT -> TOP
                INDEX_EDGE_TOP -> RIGHT
                INDEX_EDGE_RIGHT -> BOTTOM
                INDEX_EDGE_BOTTOM -> LEFT
                else -> TOP
            }
        }
        fun beforeIndex(index: Int): Int {
            return when(index) {
                INDEX_EDGE_LEFT -> INDEX_EDGE_BOTTOM
                INDEX_EDGE_TOP -> INDEX_EDGE_LEFT
                INDEX_EDGE_RIGHT -> INDEX_EDGE_TOP
                INDEX_EDGE_BOTTOM -> INDEX_EDGE_RIGHT
                else -> INDEX_EDGE_LEFT
            }
        }
        fun nextIndex(index: Int): Int {
            return when(index) {
                INDEX_EDGE_LEFT -> INDEX_EDGE_TOP
                INDEX_EDGE_TOP -> INDEX_EDGE_RIGHT
                INDEX_EDGE_RIGHT -> INDEX_EDGE_BOTTOM
                INDEX_EDGE_BOTTOM -> INDEX_EDGE_LEFT
                else -> INDEX_EDGE_LEFT
            }
        }

    }

    enum class Corner(private val index: Int) {

        TOP_LEFT(INDEX_CORNER_TOP_LEFT),
        TOP_RIGHT(INDEX_CORNER_TOP_RIGHT),
        BOTTOM_LEFT(INDEX_CORNER_BOTTOM_LEFT),
        BOTTOM_RIGHT(INDEX_CORNER_BOTTOM_RIGHT);

        fun getIndex(): Int = index

        companion object {
            @JvmStatic
            fun byIndex(index: Int): Corner {
                return when(index) {
                    INDEX_CORNER_TOP_LEFT -> TOP_LEFT
                    INDEX_CORNER_TOP_RIGHT -> TOP_RIGHT
                    INDEX_CORNER_BOTTOM_LEFT -> BOTTOM_LEFT
                    INDEX_CORNER_BOTTOM_RIGHT -> BOTTOM_RIGHT
                    else -> throw IndexOutOfBoundsException()
                }
            }
        }

        fun isCorner(index: Int): Boolean {
            return index in intArrayOf(INDEX_CORNER_TOP_LEFT, INDEX_CORNER_TOP_RIGHT, INDEX_CORNER_BOTTOM_RIGHT, INDEX_CORNER_BOTTOM_LEFT)
        }

        fun before(index: Int): Corner {
            return when(index) {
                INDEX_CORNER_TOP_LEFT -> BOTTOM_LEFT
                INDEX_CORNER_TOP_RIGHT -> TOP_LEFT
                INDEX_CORNER_BOTTOM_RIGHT -> TOP_RIGHT
                INDEX_CORNER_BOTTOM_LEFT -> BOTTOM_RIGHT
                else -> TOP_LEFT
            }
        }
        fun next(index: Int): Corner {
            return when(index) {
                INDEX_CORNER_TOP_LEFT -> TOP_RIGHT
                INDEX_CORNER_TOP_RIGHT -> BOTTOM_RIGHT
                INDEX_CORNER_BOTTOM_RIGHT -> BOTTOM_LEFT
                INDEX_CORNER_BOTTOM_LEFT -> TOP_LEFT
                else -> TOP_LEFT
            }
        }
        fun beforeIndex(index: Int): Int {
            return when(index) {
                INDEX_CORNER_TOP_LEFT -> INDEX_CORNER_BOTTOM_LEFT
                INDEX_CORNER_TOP_RIGHT -> INDEX_CORNER_TOP_LEFT
                INDEX_CORNER_BOTTOM_RIGHT -> INDEX_CORNER_TOP_RIGHT
                INDEX_CORNER_BOTTOM_LEFT -> INDEX_CORNER_BOTTOM_RIGHT
                else -> INDEX_CORNER_TOP_LEFT
            }
        }
        fun nextIndex(index: Int): Int {
            return when(index) {
                INDEX_CORNER_TOP_LEFT -> INDEX_CORNER_TOP_RIGHT
                INDEX_CORNER_TOP_RIGHT -> INDEX_CORNER_BOTTOM_RIGHT
                INDEX_CORNER_BOTTOM_RIGHT -> INDEX_CORNER_BOTTOM_LEFT
                INDEX_CORNER_BOTTOM_LEFT -> INDEX_CORNER_TOP_LEFT
                else -> INDEX_CORNER_TOP_LEFT
            }
        }

    }

    fun nextPathIndex(index: Int) {
        when(index) {
            INDEX_EDGE_LEFT -> INDEX_CORNER_TOP_LEFT
            INDEX_CORNER_TOP_LEFT -> INDEX_EDGE_TOP
            INDEX_EDGE_TOP -> INDEX_CORNER_TOP_RIGHT
            INDEX_CORNER_TOP_RIGHT -> INDEX_EDGE_RIGHT
            INDEX_EDGE_RIGHT -> INDEX_CORNER_BOTTOM_RIGHT
            INDEX_CORNER_BOTTOM_RIGHT -> INDEX_EDGE_BOTTOM
            INDEX_EDGE_BOTTOM -> INDEX_CORNER_BOTTOM_LEFT
            INDEX_CORNER_BOTTOM_LEFT -> INDEX_EDGE_LEFT
            else -> INDEX_EDGE_LEFT
        }
    }
    fun beforePathIndex(index: Int) {
        when(index) {
            INDEX_EDGE_LEFT -> INDEX_CORNER_BOTTOM_LEFT
            INDEX_CORNER_TOP_LEFT -> INDEX_EDGE_LEFT
            INDEX_EDGE_TOP -> INDEX_CORNER_TOP_LEFT
            INDEX_CORNER_TOP_RIGHT -> INDEX_EDGE_TOP
            INDEX_EDGE_RIGHT -> INDEX_CORNER_TOP_RIGHT
            INDEX_CORNER_BOTTOM_RIGHT -> INDEX_EDGE_RIGHT
            INDEX_EDGE_BOTTOM -> INDEX_CORNER_BOTTOM_RIGHT
            INDEX_CORNER_BOTTOM_LEFT -> INDEX_EDGE_BOTTOM
            else -> INDEX_EDGE_LEFT
        }
    }

}