package diken.nyarticles.ui.main.articledetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
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

        setUpTitleAuthorAndUrl()
        setUpViewPagerToDisplayPhotos()
        setUpTabLayoutForViewPager()

        return binding.root
    }

    private fun setUpTabLayoutForViewPager() {
        TabLayoutMediator(
            binding.articlesDetailFrgTabLyt,
            binding.articlesDetailFrgViewPagerForPhotos
        ) { _, _ -> }.attach()
    }

    private fun setUpViewPagerToDisplayPhotos() {
        val adapter = PhotosViewPagerAdapter(
            this, article.media
        )
        binding.articlesDetailFrgViewPagerForPhotos.adapter = adapter
        binding.articlesDetailFrgNoPhotosFoundTV.isVisible = adapter.itemCount <= 0

        setUpListenerToChangeCaption()


    }

    private fun setUpListenerToChangeCaption() {
        binding.articlesDetailFrgViewPagerForPhotos.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    setCorrectImageCaption(position)
                }
            })
    }

    private fun setCorrectImageCaption(position: Int) {
        try {
            binding.articlesDetailFrgPhotoCaptionTV.text =
                String.format("\"%s\"", article.media[position].caption)
        } catch (e: IndexOutOfBoundsException) {
        }
    }

    private fun setUpTitleAuthorAndUrl() {
        binding.articleDetailFrgUrlTV.text = article.url
        binding.articlesDetailFrgTitleTV.text = article.title
        binding.articlesDetailFrgAuthorTV.text = article.byline
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}