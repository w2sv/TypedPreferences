@file:Suppress("unused")

package com.w2sv.typedpreferences.descendants

import android.content.SharedPreferences
import androidx.core.content.edit
import com.w2sv.typedpreferences.TypedPreferences

abstract class StringPreferences<T: String?>(defaults: MutableMap<String, T>) : TypedPreferences<T>(defaults) {

    override fun SharedPreferences.writeValue(key: String, value: T, synchronously: Boolean) {
        edit(synchronously){
            putString(
                key,
                value
            )
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun SharedPreferences.getValue(key: String, defaultValue: T): T =
        getString(
            key,
            defaultValue
        ) as T
}