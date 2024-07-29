package com.diberardino.jbomb.utility

import kotlin.reflect.KProperty

object Extensions {
    fun Any.toMap(): Map<String, String> {
        return this::class.members
            .filterIsInstance<KProperty<*>>()
            .associate { prop ->
            prop.name to when(val value = prop.getter.call(this)) {
                is Iterable<*> -> value.joinToString { it?.toString() ?: "" }
                is Array<*> -> value.joinToString { it?.toString() ?: "" }
                is Map<*, *> -> value.entries.joinToString(", ") { (k, v) -> (k?.toString() ?: "") + "=" + (v?.toString() ?: "") }
                else -> value?.toString() ?: ""
            }
        }
    }


    fun Map<String, String>.getOrTrim(key: String) : String? {
        return get(keys.find { it.trim() == key.trim() })
    }
}