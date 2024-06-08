package io.github.johannrosenberg.insite.models

data class PostDetails(
    var id: String = "",
    val title: String = "",
    val description: String = "",
    val discussionUrl: String = "",
    val solution: String = "",
    val author: Author = Author(),
    val level: Levels = Levels.MODERATE
)
