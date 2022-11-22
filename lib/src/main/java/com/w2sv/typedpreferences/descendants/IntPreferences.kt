@file:Suppress("unused")

package com.w2sv.typedpreferences.descendants

import android.content.SharedPreferences
import androidx.core.content.edit
import com.w2sv.typedpreferences.TypedPreferences

abstract class IntPreferences(
    vararg preferenceDefault: Pair<String, Int>,
    sharedPreferences: SharedPreferences
) :
    TypedPreferences<Int>(
        mutableMapOf(*preferenceDefault),
        sharedPreferences
    ) {

    override fun SharedPreferences.writeValue(key: String, value: Int, synchronously: Boolean) {
        edit(synchronously) {
            putInt(key, value)
        }
    }

    override fun SharedPreferences.getValue(key: String, defaultValue: Int): Int =
        getInt(key, defaultValue)
}