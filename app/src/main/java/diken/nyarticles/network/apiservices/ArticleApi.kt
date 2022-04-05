package diken.nyarticles.network.apiservices

import diken.nyarticles.network.response.viewedarticleresponse.ViewedArticleResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticleApi {

    @GET("svc/mostpopular/v2/viewed/0.json")
    suspend fun getPopularArticle(
        @Query("api-key") apiKey: String = API_KEY
    ): Response<ViewedArticleResponse>

    companion object {
        private const val API_KEY = "AEBRAABki2KAlk7jWgYIg7wyZzvp8WjD"
    }

}