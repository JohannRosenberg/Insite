package io.github.johannrosenberg.insite.utils

fun String.capitalize(): String {
   val words = this.split(" ")
    var newWords = ""

    words.forEach {
        newWords += (" " + it.substring(0, 1).uppercase() + it.substring(1))
    }

    return newWords.trim()
}