package io.github.johannrosenberg.insite.ui.screens.home

import androidx.lifecycle.ViewModel
import coil.annotation.ExperimentalCoilApi
import io.github.johannrosenberg.jetmagic.composables.IImageManager
import io.github.johannrosenberg.jetmagic.composables.ImageManager

@OptIn(ExperimentalCoilApi::class)
class HomeViewModel : ViewModel(), IImageManager {

    @OptIn(ExperimentalCoilApi::class)
    private val imageMan = ImageManager()


    override val imageManager: ImageManager
        get() = imageMan

}