package com.example.dongnaegoyang.ui.cat_detail.post

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.dongnaegoyang.data.remote.model.response.Post
import com.example.dongnaegoyang.databinding.ItemCatDetailPostBinding

// 고양이 상세 페이지: '오늘 기록' 탭 게시글 어댑터
class PostAdapter(val viewModel: CatDetailPostViewModel):
    PagingDataAdapter<Post, PostAdapter.PostViewHolder>(PostDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            ItemCatDetailPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    fun getFirstPostTime(): String? {
        return try {
            getItem(0)?.createdTime
        } catch (e: IndexOutOfBoundsException) {
            null
        }
    }

    inner class PostViewHolder(private val binding: ItemCatDetailPostBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Post) {
            with(binding) {
                post = item
                vm = viewModel
                executePendingBindings()
            }
        }
    }

    class PostDiffCallback : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean =
            oldItem.postIdx == newItem.postIdx
        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean =
            oldItem == newItem
    }
}