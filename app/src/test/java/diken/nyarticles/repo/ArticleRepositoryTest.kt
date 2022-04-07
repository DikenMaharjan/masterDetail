package diken.nyarticles.repo

import com.google.gson.Gson
import diken.nyarticles.network.apiservices.ArticleApi
import diken.nyarticles.network.response.viewedarticleresponse.ViewedArticleResponse
import diken.nyarticles.ui.main.articles.paging.SampleResponse
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

class ArticleRepositoryTest {

    @Mock
    lateinit var articleApi: ArticleApi
    private lateinit var repository: ArticleRepository

    private val response =
        Gson().fromJson(SampleResponse.response, ViewedArticleResponse::class.java)

    @Before
    fun init() {
        MockitoAnnotations.openMocks(this)
        repository = ArticleRepository(articleApi)
        runBlocking {
            Mockito.`when`(articleApi.getPopularArticle()).thenReturn(
                Response.success(response)
            )
        }
    }


}
