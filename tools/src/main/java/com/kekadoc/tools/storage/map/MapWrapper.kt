package com.kekadoc.tools.storage.map

open class MapWrapper<K, V> @JvmOverloads constructor(private var map: MutableMap<K, V?> = mutableMapOf()) : MutableMap<K, V?> {

    protected open fun onItemAdded(key: K, value: V?) {

    }
    protected open fun onItemRemoved(key: K, value: V?) {

    }

    override val size: Int
        get() = map.size
    override val entries: MutableSet<MutableMap.MutableEntry<K, V?>>
        get() = map.entries
    override val keys: MutableSet<K>
        get() = map.keys
    override val values: MutableCollection<V?>
        get() = map.values

    override fun containsKey(key: K): Boolean = map.containsKey(key)
    override fun containsValue(value: V?): Boolean = map.containsValue(value)
    override fun get(key: K): V? = map[key]
    override fun remove(key: K): V? {
        val v = map.remove(key);
        onItemRemoved(key, v)
        return v
    }
    override fun isEmpty(): Boolean {
        return map.isEmpty()
    }
    override fun put(key: K, value: V?): V? {
        val old = map.put(key, value)
        old?.let { onItemRemoved(key, old) }
        onItemAdded(key, value)
        return old
    }
    override fun putAll(from: Map<out K, V?>) {
        for (entry in from)
            put(entry.key, entry.value)
    }
    override fun clear() {
        for (entry in map) remove(entry.key)
    }

}