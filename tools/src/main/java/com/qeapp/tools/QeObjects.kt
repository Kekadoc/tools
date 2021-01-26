package com.qeapp.tools

import java.util.*

object QeObjects {

    fun requireNonNull(vararg objects: Any) {
        for (obj in objects) Objects.requireNonNull(obj, obj.javaClass.simpleName + " - is null")
    }

    fun <T> tryCast(obj: Any?): T? {
        return tryCastSafety(obj, false)
    }
    @Suppress("UNCHECKED_CAST")
    fun <T> tryCastSafety(obj: Any?, safety: Boolean): T? {
        return try {
            obj as T?
        } catch (e: ClassCastException) {
            if (safety) {
                e.printStackTrace()
                null
            } else throw e
        }
    }
    @Suppress("UNCHECKED_CAST")
    fun <T> tryCast(obj: Any, exception: String?): T? {
        return try {
            obj as T
        } catch (e: ClassCastException) {
            throw RuntimeException(exception)
        }
    }
    @Suppress("UNCHECKED_CAST")
    fun <T> tryCastDef(obj: Any, def: T?): T? {
        return try {
            obj as T
        } catch (e: ClassCastException) {
            def
        }
    }

    @JvmStatic fun toSimpleString(o: Any?): String {
        if (o == null) return "null"
        return if (o.javaClass.isAnonymousClass) o.javaClass.name + "@" + Integer.toHexString(o.hashCode())
        else o.javaClass.simpleName + "@" + Integer.toHexString(o.hashCode())
    }

}