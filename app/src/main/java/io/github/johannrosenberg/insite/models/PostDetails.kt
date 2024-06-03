package io.github.johannrosenberg.insite.models

data class PostDetails(
    val title: String,
    val description: String,
    val points: List<String>,
    val author: Author,
)