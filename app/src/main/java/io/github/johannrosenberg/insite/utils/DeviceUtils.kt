package io.github.johannrosenberg.insite.utils

import android.content.res.Resources
import android.util.DisplayMetrics
import android.view.View
import android.view.Window
import io.github.johannrosenberg.insite.App

class DeviceUtils {
    companion object {

        val displayMetrics: DisplayMetrics by lazy { Resources.getSystem().displayMetrics }

        fun enableFullScreenMode() {
            val window = App.context.currentActivity?.window as Window

            val flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY

            window.decorView.systemUiVisibility = flags
        }

        fun disableFullScreenMode() {
            val window = App.context.currentActivity?.window as Window

            val flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY

            window.clearFlags(flags)
            window.decorView.systemUiVisibility = 0
        }
    }
}