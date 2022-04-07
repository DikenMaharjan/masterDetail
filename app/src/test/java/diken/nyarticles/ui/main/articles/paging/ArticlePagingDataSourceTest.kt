package diken.nyarticles.ui.main.articles.paging

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.google.gson.Gson
import diken.nyarticles.network.apiservices.ArticleApi
import diken.nyarticles.network.response.viewedarticleresponse.ViewedArticleResponse
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.HttpException
import retrofit2.Response

class ArticlePagingDataSourceTest {

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var articleApi: ArticleApi
    lateinit var pagingDataSource: ArticlePagingDataSource

    private val expectedResponse =
        Gson().fromJson(SampleResponse.response, ViewedArticleResponse::class.java)

    @Before
    fun initializeApi() {
        MockitoAnnotations.openMocks(this)
        pagingDataSource = ArticlePagingDataSource(articleApi)
        runBlocking {


            Mockito.`when`(articleApi.getPopularArticle()).thenReturn(
                Response.success(
                    expectedResponse
                )
            )

        }
    }

    @Test
    fun testIfLoadReturnErrorOnServerError() {
        runBlocking {
            Mockito.`when`(articleApi.getPopularArticle())
                .thenThrow(
                    HttpException(
                        Response.error<ViewedArticleResponse>(
                            500,
                            "Test Server Error".toResponseBody("text/plain".toMediaTypeOrNull())
                        )
                    )
                )
            assert(
                pagingDataSource.load(
                    PagingSource.LoadParams.Refresh(
                        key = null,
                        loadSize = 2,
                        placeholdersEnabled = false
                    )
                ) is PagingSource.LoadResult.Error
            )

        }
    }

    @Test
    fun testReturnValidPageOnSuccess() {
        runBlocking {

            Assert.assertEquals(
                PagingSource.LoadResult.Page(expectedResponse.results, null, null),
                pagingDataSource.load(
                    PagingSource.LoadParams.Refresh(
                        key = null, 5, false
                    )
                )
            )
        }
    }

    @Test
    fun testPreviousKeyAndNextKeyIsNull() {
        runBlocking {

            val a = pagingDataSource.load(
                PagingSource.LoadParams.Refresh(
                    key = null, 4, false
                )
            )
            if (a is PagingSource.LoadResult.Page) {
                assert(a.nextKey == null)
                assert(a.prevKey == null)
            }
        }
    }

    @Test
    fun checkRefreshKeyIsNull() {
        assert(
            pagingDataSource.getRefreshKey(
                PagingState(listOf(), null, PagingConfig(4), 0)
            ) == null
        )
    }

}