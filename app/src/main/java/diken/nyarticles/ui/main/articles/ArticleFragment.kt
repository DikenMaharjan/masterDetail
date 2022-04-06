package diken.nyarticles.ui.main.articles

import android.os.Bundle
import android.view.*
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import diken.nyarticles.R
import diken.nyarticles.databinding.FragmentArticlesBinding
import diken.nyarticles.ui.main.articles.recyclerview.ArticlesRVA
import diken.nyarticles.utils.RecyclerViewLoadStateAdapter
import kotlinx.coroutines.launch


@AndroidEntryPoint
class ArticleFragment : Fragment() {
    private var _binding: FragmentArticlesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ArticleFragmentViewModel by viewModels()
    private val articlesRVA = ArticlesRVA()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArticlesBinding.inflate(layoutInflater)
        setHasOptionsMenu(true)
        setUpRecyclerView()
        observeArticles()
        observeAdapterLoadStates()
        return binding.root
    }

    private fun observeAdapterLoadStates() {
        articlesRVA.addLoadStateListener { combinedLoadStates ->
            binding.articlesFrgLoadStateLyt.apply {
                loadStateLytLoadingProgressBar.isVisible =
                    combinedLoadStates.refresh is LoadState.Loading
                loadStateLytRetryTextView.isVisible = combinedLoadStates.refresh is LoadState.Error
                loadStateLytRetryImageView.isVisible =
                    combinedLoadStates.refresh is LoadState.Error
                loadStateLytRetryImageView.setOnClickListener {
                    articlesRVA.refresh()
                }
            }
        }
    }

    private fun observeArticles() {
        viewModel.articlePageLiveData.observe(viewLifecycleOwner) {
            lifecycleScope.launch {
                articlesRVA.submitData(it)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.article_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.articleMenu_refresh -> articlesRVA.refresh()
        }
        return false
    }

    private fun setUpRecyclerView() {
        binding.articlesFrgArticlesRV.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            this.adapter = articlesRVA.withLoadStateFooter(
                RecyclerViewLoadStateAdapter { articlesRVA.retry() }
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}