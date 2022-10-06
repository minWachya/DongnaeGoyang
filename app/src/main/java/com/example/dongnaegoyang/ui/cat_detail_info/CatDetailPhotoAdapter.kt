package com.example.dongnaegoyang.ui.cat_detail_info

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dongnaegoyang.data.remote.model.response.PhotoList
import com.example.dongnaegoyang.databinding.ItemCatDetailPhotoBinding

// 고양이 상세 페에지: '정보' 탭 사진 어댑터
class CatDetailPhotoAdapter :
    ListAdapter<PhotoList, CatDetailPhotoAdapter.CatDetailPhotoViewHolder>(
        PhotoListDiffCallback()
    ) {
    private lateinit var binding: ItemCatDetailPhotoBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatDetailPhotoViewHolder {
        binding = ItemCatDetailPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CatDetailPhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CatDetailPhotoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    // 사진 로드하기
    inner class CatDetailPhotoViewHolder(private val binding: ItemCatDetailPhotoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(photo: PhotoList) {
            binding.photo = photo
        }
    }

    class PhotoListDiffCallback : DiffUtil.ItemCallback<PhotoList>() {
        override fun areItemsTheSame(oldItem: PhotoList, newItem: PhotoList): Boolean {
            return oldItem.imageIdx == newItem.imageIdx
        }

        override fun areContentsTheSame(oldItem: PhotoList, newItem: PhotoList): Boolean {
            return oldItem == newItem
        }
    }
}