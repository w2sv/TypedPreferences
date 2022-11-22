@file:Suppress("unused")

package com.w2sv.typedpreferences.descendants

import android.content.SharedPreferences
import android.net.Uri
import androidx.core.content.edit
import com.w2sv.typedpreferences.TypedPreferences

abstract class UriPreferences<T : Uri?>(
    vararg preferenceDefault: Pair<String, T>,
    sharedPreferences: SharedPreferences
) :
    TypedPreferences<T>(
        mutableMapOf(*preferenceDefault),
        sharedPreferences
    ) {

    override fun SharedPreferences.writeValue(key: String, value: T, synchronously: Boolean) {
        edit(synchronously) {
            putString(
                key,
                value?.toString()
            )
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun SharedPreferences.getValue(key: String, defaultValue: T): T =
        getString(
            key,
            defaultValue?.toString()
        )
            ?.run { Uri.parse(this) } as T
}