@file:Suppress("unused")

package com.w2sv.typedpreferences.extensions

import android.content.Context
import android.content.SharedPreferences

fun Context.applicationPreferences(): SharedPreferences =
    getSharedPreferences(packageName, Context.MODE_PRIVATE)