package com.kekadoc.tools.storage

object Storage {

    interface StorageSwitcher {
        fun onNotContainer()
        fun onCollection()
        fun onMap()
        fun onArray()
    }

    @JvmStatic
    fun switchStorage(clazz: Class<*>, switcher: StorageSwitcher) {
        if (clazz.isArray) switcher.onArray() else if (MutableCollection::class.java.isAssignableFrom(clazz)) switcher.onCollection() else if (MutableMap::class.java.isAssignableFrom(clazz)) switcher.onMap() else switcher.onNotContainer()
    }
    @JvmStatic
    fun isStorage(clazz: Class<*>?): Boolean {
        return if (clazz == null) false else clazz.isArray || MutableCollection::class.java.isAssignableFrom(clazz) || MutableMap::class.java.isAssignableFrom(clazz)
    }
    @JvmStatic
    fun <T> isStorage(`object`: Any?): Boolean {
        return if (`object` == null) false else `object`.javaClass.isArray || `object` is Collection<*> || `object` is Map<*, *>
    }

}