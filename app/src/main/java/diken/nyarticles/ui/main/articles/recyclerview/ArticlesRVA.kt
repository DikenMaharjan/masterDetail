package diken.nyarticles.ui.main.articles.recyclerview

import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import diken.nyarticles.R
import diken.nyarticles.databinding.LayoutArticlesRvBinding
import diken.nyarticles.network.response.viewedarticleresponse.Article

class ArticlesRVA :
    PagingDataAdapter<Article, ArticlesRVA.Holder>(ArticlesDiffCallBack()) {
    class Holder(
        private val binding: LayoutArticlesRvBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private val context = binding.root.context



        fun bindView(article: Article) {
            binding.articlesRVLytTitleTV.text = article.title
            binding.articlesRVLytAuthorTV.text = article.byline
            binding.articlesRVLytDateTV.text = article.published_date
            setImage(article)
        }

        private fun getGrayDrawable(): ColorDrawable {
            return ColorDrawable(
                ContextCompat.getColor(
                    context,
                    R.color.gray_300
                )
            )
        }

        private fun setImage(article: Article) {
            val imageUrl = try {
                article.media[0].mediaMetadata[0].url
            } catch (e: IndexOutOfBoundsException) {
                ""
            }

            Glide.with(binding.root.context)
                .load(imageUrl)
                .placeholder(getGrayDrawable())
                .error(getGrayDrawable())
                .into(binding.articlesRVLytPhotoIV)
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