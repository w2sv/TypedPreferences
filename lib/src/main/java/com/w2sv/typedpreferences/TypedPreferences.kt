package com.w2sv.typedpreferences

import android.content.SharedPreferences
import slimber.log.i

/**
 * Base for map delegator objects interfacing with [SharedPreferences]
 */
abstract class TypedPreferences<T>(
    protected val defaults: MutableMap<String, T>,
    sharedPreferences: SharedPreferences
) : MutableMap<String, T> by defaults {

    /**
     * Keep track of which values have changed since last call to [writeChangedValues]
     * to reduce number of IO operations
     */
    private val lastDiscSyncState: MutableMap<String, T>

    init {
        forEach { (key, defaultValue) ->
            put(key, sharedPreferences.getValue(key, defaultValue))
        }
        lastDiscSyncState = toMutableMap()

        i { "Initialized ${javaClass.name}: $this" }
    }

    fun writeChangedValues(sharedPreferences: SharedPreferences, synchronously: Boolean = false) =
        entries
            .filter { lastDiscSyncState.getValue(it.key) != it.value }
            .forEach {
                sharedPreferences.writeValue(it.key, it.value, synchronously)
                i { "Wrote ${it.key}=${it.value} to shared preferences" }

                lastDiscSyncState[it.key] = it.value
            }

    /**
     * Type-specific value fetching from and writing to [SharedPreferences]
     */
    protected abstract fun SharedPreferences.writeValue(
        key: String,
        value: T,
        synchronously: Boolean
    )

    protected abstract fun SharedPreferences.getValue(key: String, defaultValue: T): T
}