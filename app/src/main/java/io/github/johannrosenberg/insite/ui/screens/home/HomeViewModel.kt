package io.github.johannrosenberg.insite.ui.screens.home

import androidx.lifecycle.ViewModel
import io.github.johannrosenberg.jetmagic.composables.IImageManager
import io.github.johannrosenberg.jetmagic.composables.ImageManager

class HomeViewModel : ViewModel(), IImageManager {

    private val imageMan = ImageManager()


    override val imageManager: ImageManager
        get() = imageMan

}