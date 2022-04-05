package diken.nyarticles.ui.main.articles.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import diken.nyarticles.network.apiservices.ArticleApi
import diken.nyarticles.network.response.viewedarticleresponse.Article
import retrofit2.HttpException
import java.io.IOException

class ArticlePagingDataSource(
    private val articleApi: ArticleApi
) : PagingSource<Int, Article>() {
    override fun getRefreshKey(state: PagingState<Int, Article>): Int {
        return 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {
            val response = articleApi.getPopularArticle()
            if (response.isSuccessful && response.body() != null) {
                LoadResult.Page(response.body()!!.results, null, null)
            } else {
                LoadResult.Error(Throwable("Something went wrong."))
            }
        } catch (e: HttpException) {
            LoadResult.Error(e)
        } catch (e: IOException) {
            LoadResult.Error(e)
        }
    }
}
