package diken.nyarticles.ui.main.articles.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import diken.nyarticles.network.apiservices.ArticleApi
import diken.nyarticles.network.response.viewedarticleresponse.Article

class ArticlePagingDataSource(
    private val articleApi: ArticleApi
) : PagingSource<Int, Article>() {
    override fun getRefreshKey(state: PagingState<Int, Article>): Int {
        return 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {

        return LoadResult.Invalid()
    }
}
