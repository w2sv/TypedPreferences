@file:Suppress("unused")

package com.w2sv.typedpreferences

import android.content.SharedPreferences
import androidx.core.content.edit
import com.w2sv.typedpreferences.TypedPreferences

abstract class BooleanPreferences(
    vararg preferenceDefault: Pair<String, Boolean>,
    sharedPreferences: SharedPreferences
) :
    TypedPreferences<Boolean>(
        *preferenceDefault,
        sharedPreferences = sharedPreferences
    ) {

    override fun SharedPreferences.writeValue(key: String, value: Boolean, synchronously: Boolean) {
        edit(synchronously) {
            putBoolean(key, value)
        }
    }

    override fun SharedPreferences.getValue(key: String, defaultValue: Boolean): Boolean =
        getBoolean(key, defaultValue)
}