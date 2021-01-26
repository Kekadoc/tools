package com.qeapp.tools.storage.map

interface MapObserving<K, V> {
    fun addMapObserver(observer: MapObserver<K, V>)
    fun removeMapObserver(observer: MapObserver<K, V>)
}