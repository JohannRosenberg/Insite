package io.github.johannrosenberg.insite.models

data class Category(
    val id: String,
    val name: String,
    val categories: List<Category>?,
)