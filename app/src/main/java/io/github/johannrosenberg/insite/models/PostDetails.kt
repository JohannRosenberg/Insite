package io.github.johannrosenberg.insite.models

data class PostDetails(
    val title: String = "",
    val description: String = "",
    val discussionUrl: String = "",
    val points: List<Point> = emptyList(),
    val author: Author = Author(),
)

data class Point(
    val text: String = "",
)