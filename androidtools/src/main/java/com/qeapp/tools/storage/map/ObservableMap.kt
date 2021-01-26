package com.qeapp.tools.storage.map

class ObservableMap<K, V> @JvmOverloads constructor(map: MutableMap<K, V?> = mutableMapOf()) : MapWrapper<K, V?>(map), MapObserving<K, V?> {

    private var observers: MutableCollection<MapObserver<K, V?>>? = null

    override fun onItemAdded(key: K, value: V?) {
        super.onItemAdded(key, value)
        observers?.forEach { it.onItemAdded(key, value) }
    }
    override fun onItemRemoved(key: K, value: V?) {
        super.onItemRemoved(key, value)
        observers?.forEach { it.onItemRemoved(key, value) }
    }

    override fun addMapObserver(observer: MapObserver<K, V?>) {
        if (observers == null) observers = linkedSetOf()
        observers!!.add(observer)
    }
    override fun removeMapObserver(observer: MapObserver<K, V?>) {
        observers?.remove(observer)
    }

}