package com.example.dongnaegoyang.ui.cat_detail.post

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dongnaegoyang.data.remote.model.response.Post
import com.example.dongnaegoyang.databinding.ItemCatDetailPostBinding

// 고양이 상세 페이지: '오늘 기록' 탭 게시글 어댑터
class CatDetailPostAdapter(private val viewModel: CatDetailPostViewModel)  :
    ListAdapter<Post, CatDetailPostAdapter.PostViewHolder>(
        PostListDiffCallback()
    ) {
    private lateinit var binding: ItemCatDetailPostBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        binding = ItemCatDetailPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class PostViewHolder(private val binding: ItemCatDetailPostBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(post: Post) {
            binding.vm = viewModel
            binding.post = post
        }
    }

    class PostListDiffCallback : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean =
            oldItem.postIdx == newItem.postIdx
        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean =
            oldItem == newItem
    }
}