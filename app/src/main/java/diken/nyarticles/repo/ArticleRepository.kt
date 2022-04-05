package diken.nyarticles.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import diken.nyarticles.network.apiservices.ArticleApi
import diken.nyarticles.ui.main.articles.paging.ArticlePagingDataSource
import javax.inject.Inject

class ArticleRepository
@Inject
constructor(
    private val articleApi: ArticleApi
) {

    fun getArticlePagingDataFlow() = Pager(
        config = PagingConfig(5)
    ) { ArticlePagingDataSource(articleApi) }.flow

}