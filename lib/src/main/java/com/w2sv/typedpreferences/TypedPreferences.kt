package com.w2sv.typedpreferences

import android.content.SharedPreferences
import slimber.log.i

/**
 * Base for map delegator objects interfacing with [SharedPreferences]
 */
abstract class TypedPreferences<T>(protected val defaults: MutableMap<String, T>) : MutableMap<String, T> by defaults {

    /**
     * init{} substitute, hence to be called before whatever sort of object usage
     *
     * Initializes values with the ones contained in [sharedPreferences] instance
     * and copies them to [lastDiscSyncState]
     */
    fun initialize(sharedPreferences: SharedPreferences) {
        i { "Initializing ${javaClass.name} from SharedPreferences" }

        forEach { (key, defaultValue) ->
            put(key, sharedPreferences.getValue(key, defaultValue))
            i { "Set ${javaClass.name}.$key to $defaultValue from SharedPreferences" }
        }
        lastDiscSyncState = toMutableMap()
    }

    fun writeChangedValues(sharedPreferences: Lazy<SharedPreferences>, synchronously: Boolean = false) =
        entries
            .filter { lastDiscSyncState.getValue(it.key) != it.value }
            .forEach {
                sharedPreferences.value.writeValue(it.key, it.value, synchronously)
                i { "Wrote ${it.key}=${it.value} to shared preferences" }

                lastDiscSyncState[it.key] = it.value
            }

    /**
     * Keep track of which values have changed since last call to [writeChangedValues]
     * to reduce number of IO operations
     */
    private lateinit var lastDiscSyncState: MutableMap<String, T>

    /**
     * Type-specific value fetching from and writing to [SharedPreferences]
     */
    protected abstract fun SharedPreferences.writeValue(key: String, value: T, synchronously: Boolean)
    protected abstract fun SharedPreferences.getValue(key: String, defaultValue: T): T
}