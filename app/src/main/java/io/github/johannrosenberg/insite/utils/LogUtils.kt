package io.github.johannrosenberg.insite.utils

import android.util.Log
import io.github.johannrosenberg.insite.BuildConfig.DEBUG

const val TAG = "Insite"

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