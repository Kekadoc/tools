package com.qeapp.tools.storage.map

interface MapObserver<K, V> {
    fun onItemAdded(key: K, value: V?)
    fun onItemRemoved(key: K, value: V?)
}