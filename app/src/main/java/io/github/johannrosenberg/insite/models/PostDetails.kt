package io.github.johannrosenberg.insite.models

data class PostDetails(
    val title: String,
    val description: String,
    val points: List<Point>,
    val author: Author,
)

data class Point(
    val text: String,
)