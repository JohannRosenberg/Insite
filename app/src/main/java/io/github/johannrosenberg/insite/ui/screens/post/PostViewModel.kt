package io.github.johannrosenberg.insite.ui.screens.post

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.johannrosenberg.insite.da.LineInfo
import io.github.johannrosenberg.insite.da.Markdown
import io.github.johannrosenberg.insite.da.Repository
import io.github.johannrosenberg.insite.models.PostDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PostViewModel : ViewModel() {
    val postDetails = mutableStateOf<PostDetails>(PostDetails())
    var getPostData = true
    var challengee = mutableListOf<LineInfo>()
    var solution = mutableListOf<LineInfo>()

    fun getPostDetails(postId: String) {
        if (!getPostData)
            return

        getPostData = false

        viewModelScope.launch(Dispatchers.IO) {
            val postData = Repository.getPostDetails(postId)
            val markdown = Markdown()
            challengee = markdown.convertMarkdownToMetaData(postData.description)
            solution = markdown.convertMarkdownToMetaData(postData.solution)

            withContext(Dispatchers.Main) {
                postDetails.value = postData
            }
        }
    }
}