package io.github.johannrosenberg.insite.ui.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.livedata.observeAsState
import io.github.johannrosenberg.insite.ui.screens.ComposableResourceIDs
import io.github.johannrosenberg.jetmagic.composables.crm
import io.github.johannrosenberg.jetmagic.models.ComposableInstance
import io.github.johannrosenberg.jetmagic.models.LocalComposableInstance

@Composable
fun HomeScreenHandler(composableInstance: ComposableInstance) {

    CompositionLocalProvider(LocalComposableInstance provides composableInstance) {

        composableInstance.onUpdate?.observeAsState()?.value

        crm.RenderChildComposable(
            parentComposableId = composableInstance.id,
            composableResId = ComposableResourceIDs.HOME,
            childComposableId = ComposableResourceIDs.HOME,
            p = composableInstance.parameters
        )
    }
}

