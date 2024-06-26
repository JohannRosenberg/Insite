package io.github.johannrosenberg.insite.models

data class Author(
    val id: String = "",
    val name: String = "",
    val bio: String = "",
    val photo: String = "",
    val url1: String? = null,
    val url2: String? = null
)
