package io.github.johannrosenberg.insite.models

data class PostDetails(
    val title: String = "",
    val description: String = "",
    val discussionUrl: String = "",
    val solution: Solution = Solution(),
    val author: Author = Author(),
)

data class Solution(
    val description: String = "",
    val keyPoints: List<KeyPoint> = emptyList(),
)

data class KeyPoint(
    val text: String = "",
)