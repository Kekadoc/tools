package com.qeapp.tools.random

import com.qeapp.tools.exeption.Wtf
import com.qeapp.tools.storage.array.QeArrays
import com.qeapp.tools.value.QeValue
import java.security.SecureRandom
import java.util.*
import kotlin.collections.ArrayList

object QeRandom {
    private const val TAG = "QeRandom-TAG"

    private fun <T> getRandomByWeightHelper(map: Map<T, Float>, sum: Float): T? {
        val random = newRandom()
        val v = (random.nextDouble() * sum)
        var old = 0.0f
        for ((key, value) in map) {
            if (v >= old && v < value) return key
            old = value
        }
        return null
    }

    @JvmStatic fun <T> getRandomByWeight(array: Array<T>?, weight: ((item: T) -> Float)?): T? {
        if (array == null || array.isEmpty()) return null
        if (weight == null) return getRandomFrom(array)

        val rand: MutableMap<T, Float> = HashMap()
        var sum = 0.0f
        for (t in array) {
            val d = weight.invoke(t)
            rand[t] = d
            sum += d
        }

        return getRandomByWeightHelper(rand, sum)
    }
    @JvmStatic fun <T> getRandomIndexByWeight(array: Array<T>?, weight: ((item: T) -> Float)?): Int {
        if (array == null || array.isEmpty()) return -1
        if (weight == null) return getRandomIndexFrom(array)

        val rand: MutableMap<Int, Float> = HashMap()
        var sum = 0.0f
        for (index in array.indices) {
            val d = weight.invoke(array[index])
            rand[index] = d
            sum += d
        }

        return getRandomByWeightHelper(rand, sum) ?: -1
    }

    @JvmStatic fun <T> getRandomByWeight(collection: Collection<T>?, weight: ((item: T) -> Float)?): T? {
        if (collection == null || collection.isEmpty()) return null
        if (weight == null) return getRandomFrom(collection)

        val rand: MutableMap<T, Float> = HashMap()
        var sum = 0.0f
        for (t in collection) {
            val d = weight.invoke(t)
            rand[t] = d
            sum += d
        }

        return getRandomByWeightHelper(rand, sum)
    }
    @JvmStatic fun <T> getRandomIndexByWeight(list: List<T>?, weight: ((item: T) -> Float)?): Int {
        if (list == null || list.isEmpty()) return -1
        if (weight == null) return getRandomIndexFrom(list)

        val rand: MutableMap<Int, Float> = HashMap()
        var sum = 0.0f
        for (index in list.indices) {
            val d = weight.invoke(list[index])
            rand[index] = d
            sum += d
        }

        return getRandomByWeightHelper(rand, sum) ?: -1
    }

    @JvmStatic fun <K, V> getRandomValueByWeight(map: Map<K, V>?, weight: ((key: K, value: V) -> Float)?): V? {
        if (map == null || map.isEmpty()) return null
        if (weight == null) return getRandomFrom(map)

        val rand: MutableMap<V, Float> = HashMap()
        var sum = 0.0f
        for (entry in map) {
            val d = weight.invoke(entry.key, entry.value)
            rand[entry.value] = d
            sum += d
        }
        return getRandomByWeightHelper(rand, sum)
    }
    @JvmStatic fun <K, V> getRandomKeyByWeight(map: Map<K, V>?, weight: ((key: K, value: V) -> Float)?): K? {
        if (map == null || map.isEmpty()) return null
        if (weight == null) return getRandomKeyFrom(map)

        val rand: MutableMap<K, Float> = HashMap()
        var sum = 0.0f
        for (entry in map) {
            val d = weight.invoke(entry.key, entry.value)
            rand[entry.key] = d
            sum += d
        }
        return getRandomByWeightHelper(rand, sum)
    }


    @JvmStatic fun <T> getRandomFrom(c: Collection<T>, count: Int): Collection<T> {
        if (c.size < count) throw RuntimeException()
        val list = ArrayList(c)
        val cR: MutableCollection<T> = ArrayList()
        val random = Random()
        for (i in 0 until count) {
            val item = list[random.nextInt(list.size)]
            cR.add(item)
            list.remove(item)
        }
        return cR
    }
    @JvmStatic fun <T> getRandomFrom(array: Array<T>, count: Int): Collection<T> {
        return getRandomFrom(listOf(*array), count)
    }
    @JvmStatic fun <K, V> getRandomFrom(map: Map<K, V>, count: Int): Collection<V> {
        if (map.size < count) throw RuntimeException()
        val list: Collection<V> = map.values
        return getRandomFrom(list, count)
    }

    @JvmStatic fun <T> getRandomFrom(c: Collection<T>?): T? {
        if (c == null || c.isEmpty()) return null
        val list = ArrayList(c)
        return list[newRandom().nextInt(list.size)]
    }
    @JvmStatic fun <T> getRandomFrom(array: Array<T>?): T? {
        return if (array == null) null else getRandomFrom(listOf(*array))
    }
    @JvmStatic fun <K, V> getRandomFrom(map: Map<K, V>?): V? {
        if (map == null) return null
        val list: Collection<V> = map.values
        return getRandomFrom(list)
    }


    @JvmStatic fun <T> getRandomIndexFrom(list: List<T>, count: Int): Collection<Int> {
        if (list.size < count) throw RuntimeException()
        val listIndex: MutableList<Int> = mutableListOf()
        for (i in list.indices) listIndex.add(i)

        val cR: MutableCollection<Int> = ArrayList()
        val random = Random()
        for (i in 0 until count) {
            val item = listIndex[random.nextInt(list.size)]
            cR.add(item)
            listIndex.remove(item)
        }
        return cR
    }
    @JvmStatic fun <T> getRandomIndexFrom(array: Array<T>, count: Int): Collection<Int> {
        return getRandomIndexFrom(listOf(*array), count)
    }
    @JvmStatic fun <K, V> getRandomIndexFrom(map: Map<K, V>, count: Int): Collection<Int> {
        if (map.size < count) throw RuntimeException()
        val list: List<V> = ArrayList(map.values)
        return getRandomIndexFrom(list, count)
    }

    @JvmStatic fun <T> getRandomIndexFrom(list: List<T>?): Int {
        if (list == null || list.isEmpty()) return -1
        return newRandom().nextInt(list.size)
    }
    @JvmStatic fun <T> getRandomIndexFrom(array: Array<T>?): Int {
        return if (array == null) -1 else newRandom().nextInt(array.size)
    }
    @JvmStatic fun <K, V> getRandomKeyFrom(map: Map<K, V>?): K? {
        if (map == null) return null
        return getRandomFrom(map.keys)
    }

    @JvmStatic fun getRandomIndex(chance: IntArray): Int {
        val s: Int = QeArrays.getSum(chance)
        val r = newRandom().nextInt(s)
        val a = 0
        var b = 0
        for (i in chance.indices) {
            b += chance[i]
            if (r in a..b) return i
        }
        throw Wtf()
    }
    @JvmStatic fun getRandomIndex(chance: FloatArray): Int {
        val s: Float = QeArrays.getSum(chance)
        val r = newRandom().nextFloat() * s
        val a = 0f
        var b = 0f
        for (i in chance.indices) {
            b += chance[i]
            if (r in a..b) return i
        }
        throw Wtf()
    }

    @JvmStatic fun newRandom(): Random {
        return Random()
    }
    @JvmStatic fun newRandom(seed: Long): Random {
        return Random(seed)
    }
    @JvmStatic fun newSecureRandom(seed: Long): SecureRandom {
        return SecureRandom()
    }


    /** Проверка шанса  */
    @JvmStatic fun tryChanceFraction(chance: Double): Boolean {
        return newRandom().nextDouble() < chance
    }

    /** Проверка шанса  */
    @JvmStatic fun tryChanceFraction(chance: Float): Boolean {
        val f = newRandom().nextFloat()
        return f < chance
    }

    /** Случайная смена знака числа  */
    @JvmStatic fun inverseFractionChance(value: Double): Double {
        return if (newRandom().nextBoolean()) -value else value
    }
    /** Случайная смена знака числа  */
    @JvmStatic fun inverseFractionChance(value: Float): Float {
        return if (newRandom().nextBoolean()) -value else value
    }

    @JvmStatic fun getRandomHard(max: Int, difficulties: Double): Int {
        val random = Random()
        val list = ArrayList<Int>()
        for (i in 0..max) {
            val dif = difficulties * (i / max.toDouble())
            var j = 0
            while (j < 1.0 + dif) {
                list.add(max - i)
                j++
            }
        }
        val r = random.nextInt(list.size)
        return list[r]
    }

    /**
     * @param end inclusive
     */
    @JvmStatic fun getRandomBetween(start: Int, end: Int): Int {
        return start + Math.round(Math.random() * (end - start)).toInt()
    }

    @JvmStatic fun getRandomArray(start: Int, end: Int, count: Int, different: Boolean): IntArray {
        if (different && QeValue.getRange(start, end) < count) throw Wtf()
        val rnd = IntArray(count)
        for (i in 0 until count) {
            var r: Int
            while (true) {
                r = getRandomBetween(start, end)
                if (different) {
                    if (!QeArrays.contain(rnd, r)) break
                } else break
            }
            rnd[i] = r
        }
        return rnd
    }

    @JvmStatic fun getRandomBetween(start: Float, end: Float): Float {
        return start + Math.round(Math.random() * (end - start)).toFloat()
    }

    @JvmStatic fun getRandoms(from: IntArray, count: Int, differents: Boolean): IntArray {
        if (count > from.size) throw Wtf()
        val list: MutableList<Int> = ArrayList()
        for (i in from) list.add(from[i])
        val r = IntArray(count)
        for (i in 0 until count) {
            val index = newRandom().nextInt(list.size)
            r[i] = list[index]
            if (differents) list.removeAt(index)
        }
        return r
    }

}