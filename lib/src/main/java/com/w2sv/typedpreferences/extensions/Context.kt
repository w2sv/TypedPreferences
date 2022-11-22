@file:Suppress("unused")

package com.w2sv.typedpreferences.extensions

import android.content.Context
import android.content.SharedPreferences

fun Context.getAppPreferences(): SharedPreferences =
    getSharedPreferences(packageName, Context.MODE_PRIVATE)