package diken.nyarticles.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import diken.nyarticles.databinding.LoadStateLayoutBinding

class RecyclerViewLoadStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<RecyclerViewLoadStateAdapter.Holder>() {
    class Holder(private val binding: LoadStateLayoutBinding, retry: () -> Unit) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.loadStateLytRetryImageView.setOnClickListener {
                retry.invoke()
            }
        }

        fun bindView(loadState: LoadState) {
            binding.loadStateLytLoadingProgressBar.isVisible = loadState is LoadState.Loading
            binding.loadStateLytRetryTextView.isVisible = loadState is LoadState.Error
            binding.loadStateLytRetryImageView.isVisible = loadState is LoadState.Error
        }

    }

    override fun onBindViewHolder(holder: Holder, loadState: LoadState) {
        holder.bindView(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): Holder {
        return Holder(
            LoadStateLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            ), retry
        )
    }
}