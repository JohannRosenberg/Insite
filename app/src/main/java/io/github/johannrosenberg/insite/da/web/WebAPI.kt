package io.github.johannrosenberg.insite.da.web

import io.github.johannrosenberg.insite.models.QuizPostings
import retrofit2.http.GET

const val APIBaseAddress = "https://raw.githubusercontent.com/JohannRosenberg/Insite/main/repository/"
const val NavMenuIconsPath = APIBaseAddress + "NavMenuIcons/"
const val PostsPath = APIBaseAddress + "Posts/"

/**
 * Retrofit API declarations.
 * NOTE: If a path starts with a forward slash, it means that it is relative to the root domain.
 * Without a prefixed forward slash, the path is appended to whatever the base url is set to.
 */
interface WebAPI {
    /**
     * Retrieves all the quiz postings including the meta data such as the categories.
     */
    @GET("repository.json")
    suspend fun getQuizPostings(): QuizPostings
}
