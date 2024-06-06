package io.github.johannrosenberg.insite.models

import io.github.johannrosenberg.insite.App
import io.github.johannrosenberg.insite.R

data class Post (
    val id: String,
    val title: String,
    val date: String,
    val category: String,
    val author: String,
    val level: Levels
)

enum class Levels(level: String) {
    EASY("easy"),
    MODERATE("moderate"),
    HARD("hard");

    override fun toString(): String {
        return when (this) {
            EASY -> App.context.getString(R.string.easy)
            MODERATE -> App.context.getString(R.string.moderate)
            HARD -> App.context.getString(R.string.hard)
        }
    }
}