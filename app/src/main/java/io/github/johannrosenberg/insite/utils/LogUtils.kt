package io.github.johannrosenberg.catlaser.utils

import android.util.Log
import io.github.johannrosenberg.insite.BuildConfig.DEBUG

const val TAG = "LaserPointer"

fun logInfo(message: String) {
    if (DEBUG) {
        Log.i(TAG, message)
    }
}

fun logError(message: String) {
    if (DEBUG) {
        Log.e(TAG, message)
    }
}