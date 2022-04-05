package diken.nyarticles.ui.main.articledetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayoutMediator
import diken.nyarticles.databinding.FragmentArticleDetailBinding
import diken.nyarticles.network.response.viewedarticleresponse.Article
import diken.nyarticles.ui.main.articledetails.photosviewpager.PhotosViewPagerAdapter


class ArticleDetailFragment : Fragment() {
    private var _binding: FragmentArticleDetailBinding? = null
    private val binding get() = _binding!!

    private val arg: ArticleDetailFragmentArgs by navArgs()
    private lateinit var article: Article

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArticleDetailBinding.inflate(layoutInflater)
        article = arg.article

        setUpTitleAndAuthor()
        setUpViewPagerToDisplayPhotos()

        return binding.root
    }

    private fun setUpViewPagerToDisplayPhotos() {
        val adapter = PhotosViewPagerAdapter(
            this, article.media
        )
        binding.articlesDetailFrgViewPagerForPhotos.adapter = adapter
        binding.articlesDetailFrgNoPhotosFoundTV.isVisible = adapter.itemCount <= 0


        TabLayoutMediator(
            binding.articlesDetailFrgTabLyt,
            binding.articlesDetailFrgViewPagerForPhotos
        ) { _, _ -> }.attach()

    }

    private fun setUpTitleAndAuthor() {
        binding.articlesDetailFrgTitleTV.text = article.title
        binding.articlesDetailFrgAuthorTV.text = article.byline
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}