package diken.nyarticles.ui.main.articles

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import diken.nyarticles.repo.ArticleRepository
import javax.inject.Inject

@HiltViewModel
class ArticleFragmentViewModel
@Inject
constructor(
    articleRepository: ArticleRepository
) : ViewModel() {

    val articlePageLiveData =
        articleRepository.getArticlePagingDataFlow().cachedIn(viewModelScope).asLiveData()

}