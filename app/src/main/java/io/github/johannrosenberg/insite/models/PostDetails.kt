package io.github.johannrosenberg.insite.models

data class PostDetails(
    val description: String = "",
    val discussionUrl: String = "",
    val solution: String = "",
    val author: Author = Author(),
)
