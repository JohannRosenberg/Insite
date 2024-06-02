package io.github.johannrosenberg.insite.ui.screens.post

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.johannrosenberg.insite.da.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PostViewModel: ViewModel() {
    fun getPostDetails(postId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val postDetails = Repository.getPostDetails(postId)
        }
    }
}