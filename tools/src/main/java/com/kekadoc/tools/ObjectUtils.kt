package com.kekadoc.tools

import java.util.*

object ObjectUtils {

    @JvmStatic
    fun requireNonNull(vararg objects: Any) {
        for (obj in objects) Objects.requireNonNull(obj, obj.javaClass.simpleName + " - is null")
    }


    @JvmStatic
    fun <T> tryCast(obj: Any?): T? {
        return tryCastSafety(obj, false)
    }

    @JvmStatic
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

    @JvmStatic
    @Suppress("UNCHECKED_CAST")
    fun <T> tryCast(obj: Any?, exception: String?): T? {
        return try {
            obj as T
        } catch (e: ClassCastException) {
            throw RuntimeException(exception)
        }
    }

    @JvmStatic
    @Suppress("UNCHECKED_CAST")
    fun <T> tryCastDef(obj: Any, def: T?): T? {
        return try {
            obj as T
        } catch (e: ClassCastException) {
            def
        }
    }

    @JvmStatic
    fun toSimpleString(o: Any?): String {
        if (o == null) return "null"
        return if (o.javaClass.isAnonymousClass) o.javaClass.name + "@" + Integer.toHexString(o.hashCode())
        else o.javaClass.simpleName + "@" + Integer.toHexString(o.hashCode())
    }

}