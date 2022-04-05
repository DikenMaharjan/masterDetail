package diken.nyarticles.ui.main.articles

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import diken.nyarticles.databinding.FragmentArticlesBinding
import diken.nyarticles.ui.main.articles.recyclerview.ArticlesRVA
import kotlinx.coroutines.launch


@AndroidEntryPoint
class ArticleFragment : Fragment() {
    private var _binding: FragmentArticlesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ArticleFragmentViewModel by viewModels()
    private val adapter = ArticlesRVA()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArticlesBinding.inflate(layoutInflater)
        setUpRecyclerView()
        observeArticles()
        return binding.root
    }

    private fun observeArticles() {
        viewModel.articlePageLiveData.observe(viewLifecycleOwner) {
            lifecycleScope.launch {
                adapter.submitData(it)
            }
        }
    }

    private fun setUpRecyclerView() {
        binding.articlesFrgArticlesRV.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            this.adapter = this@ArticleFragment.adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}