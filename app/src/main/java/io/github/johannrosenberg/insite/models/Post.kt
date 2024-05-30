package io.github.johannrosenberg.insite.models

data class Post (
    val id: String,
    val title: String,
    val date: String,
    val category: String,
    val author: String
)