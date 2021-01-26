package com.qeapp.tools.storage.map

import com.qeapp.tools.builder.QeInstanceBuilder
import java.util.*

object QeMap {

    @JvmStatic
    fun <K, V> buildMap(): MapBuilder<K, V> = MapBuilder(HashMap())
    @JvmStatic
    fun <K, V> buildMap(map: MutableMap<K, V>): MapBuilder<K, V> = MapBuilder(map)

    @JvmStatic
    fun <K, V> isEmpty(map: Map<K, V>?): Boolean {
        return map == null || map.isEmpty()
    }

    @JvmStatic
    fun <K, V> isContains(container: Map<K, V>, elements: Map<K, V>): Boolean {
        for (key in elements.keys) if (container.containsValue(elements[key])) return true
        return false
    }

    @JvmStatic
    fun <K, V> mapOf(key: K, value: V): MapBuilder<K, V> {
        return MapBuilder(HashMap<K, V>()).put(key, value)
    }

    class MapBuilder<K, V>(instance: MutableMap<K, V>) : QeInstanceBuilder<MutableMap<K, V>>(instance) {
        fun put(key: K, value: V): MapBuilder<K, V> {
            instance()[key] = value
            return this
        }
    }

}