package io.github.johannrosenberg.insite

import android.app.Activity
import android.app.Application
import android.os.Bundle
import io.github.johannrosenberg.insite.ui.screens.ComposableResourceIDs
import io.github.johannrosenberg.insite.ui.screens.home.HomeHandler
import io.github.johannrosenberg.insite.ui.screens.home.HomeScreenHandler
import io.github.johannrosenberg.insite.ui.screens.home.HomeViewModel
import io.github.johannrosenberg.insite.ui.screens.main.MainViewModel
import io.github.johannrosenberg.jetmagic.composables.crm
import io.github.johannrosenberg.jetmagic.initializeJetmagic
import io.github.johannrosenberg.jetmagic.models.ComposableResource
import java.util.Locale

/**
 * Inherits from Application and is used for things like accessing the app's context and setting up
 * resources during the app's startup.
 */
class App : Application() {

    private val activityLifecycleTracker: AppLifecycleTracker = AppLifecycleTracker()


    override fun onCreate() {
        super.onCreate()
        context = this

        initializeJetmagic(this)

        registerActivityLifecycleCallbacks(activityLifecycleTracker)

        /**
         * Define all the screens and those composables used as the root composables
         * on screens. The order in which the composable resources are added is not
         * important, but visually it is useful if the screen resources are listed
         * first followed by those composable resources that are used on those
         * screens.
         */

        crm.useDefaultAnimation = false
        crm.apply {
            addComposableResources(
                mutableListOf(
                    ComposableResource(
                        resourceId = ComposableResourceIDs.HOME_SCREEN,
                    ) { composableInstance ->
                        // Home screen.
                        HomeScreenHandler(composableInstance)
                    },


                    // ************** List all children resources. **************
                    ComposableResource(
                        resourceId = ComposableResourceIDs.HOME,
                        viewmodelClass = HomeViewModel::class.java
                    ) { composableInstance ->
                        // Home default
                        HomeHandler(composableInstance)
                    },
                )
            )
        }
    }

    companion object {
        lateinit var context: App
        lateinit var mainViewModel: MainViewModel
        var appLocale = "en"
    }

    // Returns the current activity.
    var currentActivity: Activity?
        get() = activityLifecycleTracker.currentActivity
        private set(value) {}


    /**
     * Callbacks for handling the lifecycle of activities.
     */
    class AppLifecycleTracker : ActivityLifecycleCallbacks {

        private var currentAct: Activity? = null

        var currentActivity: Activity?
            get() = currentAct
            private set(value) {}

        override fun onActivityCreated(activity: Activity, p1: Bundle?) {
            currentAct = activity
        }

        override fun onActivityStarted(activity: Activity) {
        }

        override fun onActivityResumed(activity: Activity) {
            currentAct = activity
            context.resources.configuration.setLocale(Locale(appLocale))
            context.resources.updateConfiguration(context.resources.configuration, context.resources.displayMetrics)
        }

        override fun onActivityPaused(p0: Activity) {
        }

        override fun onActivityStopped(activity: Activity) {
            if ((currentAct != null) && (activity == currentAct))
                currentAct = null
        }

        override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {
        }

        override fun onActivityDestroyed(p0: Activity) {
        }
    }
}

