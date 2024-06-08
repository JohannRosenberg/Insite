package io.github.johannrosenberg.insite.da.web

import io.github.johannrosenberg.insite.models.PostDetails
import io.github.johannrosenberg.insite.models.QuizPostings
import retrofit2.http.GET
import retrofit2.http.Url

const val API_BASE_ADDRESS = "https://raw.githubusercontent.com/JohannRosenberg/Insite/main/repository/"
const val NAV_MENU_ICON_PATH = API_BASE_ADDRESS + "NavMenuIcons/"
const val IMAGES_PATH = API_BASE_ADDRESS + "images/"
const val POST_PATH = API_BASE_ADDRESS + "Posts/"
const val AUTHOR_PHOTO_PATH = API_BASE_ADDRESS + "author-photos/"

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

    @GET
    suspend fun getPostDetails(@Url url: String): PostDetails
}
