package com.qeapp.tools.storage.array

import java.util.*
import kotlin.random.Random

object QeArrays {
    private const val TAG = "QeArrays-TAG"

    fun <T> arrayToString(array: Array<T>?): String? {
        if (array == null) return "null"
        val builder = StringBuilder()
                .append(QeUtil.toSimpleString(array)).append(" size: ").append(array.size).append('\n')
        for (obj in array) {
            builder.append(obj.toString())
            builder.append('\n')
        }
        return builder.toString()
    }

    @JvmStatic
    fun <T> addToArray(array: Array<T?>, element: T): Boolean {
        for (i in array.indices) {
            if (array[i] == null) {
                array[i] = element
                return true
            }
        }
        return false
    }

    @JvmStatic
    fun <T> removeInArray(array: Array<T?>, element: T): Boolean {
        for (i in array.indices) {
            if (array[i] == element) {
                array[i] = null
                return true
            }
        }
        return false
    }

    @JvmStatic
    fun <T> isContainNull(array: Array<T>): Boolean {
        for (t in array) if (t == null) return true
        return false
    }

    @JvmStatic
    fun reverse(array: IntArray): IntArray {
        val size = array.size
        val arrayReverse = IntArray(array.size)
        for (i in 0 until size) {
            arrayReverse[i] = array[size - 1 - i]
        }
        return arrayReverse
    }
    @JvmStatic
    fun reverse(array: FloatArray): FloatArray {
        val size = array.size
        val arrayReverse = FloatArray(array.size)
        for (i in 0 until size) {
            arrayReverse[i] = array[size - 1 - i]
        }
        return arrayReverse
    }
    @JvmStatic
    fun reverse(array: LongArray): LongArray {
        val size = array.size
        val arrayReverse = LongArray(array.size)
        for (i in 0 until size) {
            arrayReverse[i] = array[size - 1 - i]
        }
        return arrayReverse
    }

    @JvmStatic
    fun contain(ints: IntArray, i: Int): Boolean {
        for (j in ints.indices) {
            if (ints[j] == i) return true
        }
        return false
    }
    @JvmStatic
    fun contain(ints: FloatArray, i: Float): Boolean {
        for (j in ints.indices) {
            if (ints[j] == i) return true
        }
        return false
    }

    @JvmStatic
    /** Содержит ли коллекция хотябы 1 элемент из другой коллекции  */
    fun <V> isContains(container: Array<V>, elements: Array<V>): Boolean {
        return Arrays.asList(*container).containsAll(Arrays.asList(*elements))
    }

    @JvmStatic
    /** Содержит ли коллекция элементы из другой коллекции  */
    fun <V> isContains(container: Array<V>, element: V): Boolean {
        return Arrays.asList(*container).contains(element)
    }

    @JvmStatic
    inline fun <K, reified V> toArray(map: Map<K, V>): Array<V> {
        return map.values.toTypedArray()
    }

    @JvmStatic
    inline fun <reified T> toArray(c: Collection<T>): Array<T> {
        return c.toTypedArray()
    }

    @JvmStatic
    fun <T> isEmpty(array: Array<T>?): Boolean {
        return array == null || array.size == 0
    }

    @JvmStatic
    fun createGeomProgress_0(start: Int, end: Int, step: Int): IntArray {
        var `val` = start
        val range = Math.abs(start) + Math.abs(end)
        var pos = 0
        val size = Math.ceil(range.toDouble() / step.toDouble()).toInt() + 1
        val arr = IntArray(size)
        do {
            arr[pos] = `val`
            pos++
            `val` += step
        } while (`val` < end)
        arr[pos] = end
        return arr
    }
    @JvmStatic
    fun createGeomProgress_1(start: Int, step: Int, count: Int): IntArray {
        val arr = IntArray(count)
        var `val` = start
        for (i in 0 until count) {
            arr[i] = `val`
            `val` += step
        }
        return arr
    }
    @JvmStatic
    fun createGeomProgress_2(start: Int, end: Int, count: Int): IntArray {
        val arr = IntArray(count)
        var `val` = start
        val range = Math.abs(start) + Math.abs(end)
        val step = range / count
        for (i in 0 until count) {
            arr[i] = `val`
            `val` += step
        }
        return arr
    }

    @JvmStatic
    fun getRandomFromArray(array: IntArray?): Int {
        if (array == null || array.isEmpty()) return 0
        val r: Int = Random.nextInt(array.size)
        return array[r]
    }

    @JvmStatic
    fun getSum(arr: IntArray): Int {
        var s = 0
        for (i in arr) s += i
        return s
    }
    @JvmStatic
    fun getSum(arr: LongArray): Long {
        var s = 0
        for (i in arr) s += i.toInt()
        return s.toLong()
    }
    @JvmStatic
    fun getSum(arr: FloatArray): Float {
        var s = 0
        for (i in arr) s += i.toInt()
        return s.toFloat()
    }
    @JvmStatic
    fun getSum(arr: DoubleArray): Double {
        var s = 0
        for (i in arr) s += i.toInt()
        return s.toDouble()
    }

    @JvmStatic
    fun <T> getLast(array: Array<T>): T? {
        return if (array.isEmpty()) null else array[array.size - 1]
    }

}