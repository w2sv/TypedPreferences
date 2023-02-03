@file:Suppress("unused")

package com.w2sv.typedpreferences

import android.content.SharedPreferences
import androidx.core.content.edit
import com.w2sv.typedpreferences.TypedPreferences

abstract class LongPreferences(
    vararg preferenceDefault: Pair<String, Long>,
    sharedPreferences: SharedPreferences
) :
    TypedPreferences<Long>(
        *preferenceDefault,
        sharedPreferences = sharedPreferences
    ) {

    override fun SharedPreferences.writeValue(key: String, value: Long, synchronously: Boolean) {
        edit(synchronously) {
            putLong(key, value)
        }
    }

    override fun SharedPreferences.getValue(key: String, defaultValue: Long): Long =
        getLong(key, defaultValue)
}