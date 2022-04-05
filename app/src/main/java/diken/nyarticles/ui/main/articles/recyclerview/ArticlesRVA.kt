package diken.nyarticles.ui.main.articles.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import diken.nyarticles.databinding.LayoutArticlesRvBinding
import diken.nyarticles.network.response.viewedarticleresponse.Article

class ArticlesRVA :
    PagingDataAdapter<Article, ArticlesRVA.Holder>(ArticlesDiffCallBack()) {
    class Holder(
        private val binding: LayoutArticlesRvBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindView(article: Article) {
            binding.articlesRVLytTitleTV.text = article.title
            binding.articlesRVLytAuthorTV.text = article.source
            binding.articlesRVLytDateTV.text = article.published_date
        }
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        getItem(position)?.let {
            holder.bindView(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            LayoutArticlesRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }
}

private class ArticlesDiffCallBack : DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(
        oldItem: Article,
        newItem: Article
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: Article,
        newItem: Article
    ): Boolean {
        return oldItem == newItem
    }

}