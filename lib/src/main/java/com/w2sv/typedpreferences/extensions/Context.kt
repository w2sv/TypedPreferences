@file:Suppress("unused")

package com.w2sv.typedpreferences.extensions

import android.content.Context
import android.content.SharedPreferences

fun Context.appPreferences(): SharedPreferences =
    getSharedPreferences(packageName, Context.MODE_PRIVATE)