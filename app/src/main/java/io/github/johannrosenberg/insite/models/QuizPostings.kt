package io.github.johannrosenberg.insite.models

data class QuizPostings(
    val categories: List<Category> = emptyList(),
    val posts: List<Post> = emptyList()
)