package com.example.dongnaegoyang.ui.cat_detail.info

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dongnaegoyang.data.remote.model.response.CustomCat
import com.example.dongnaegoyang.databinding.ItemAnotherCatBinding

// 고양이 상세: 정보 탭 - 나도 관심 좀... 어댑터
class CatDetailInfoAnotherCatAdapter(val viewModel: CatDetailInfoViewModel)  :
    ListAdapter<CustomCat, CatDetailInfoAnotherCatAdapter.CatDetailInfoViewHolder>(
        CustomCatDiffCallback()
    ) {
    private lateinit var binding: ItemAnotherCatBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatDetailInfoViewHolder {
        binding = ItemAnotherCatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CatDetailInfoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CatDetailInfoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CatDetailInfoViewHolder(private val binding: ItemAnotherCatBinding): RecyclerView.ViewHolder(binding.root)  {
        fun bind(cat: CustomCat) {
            binding.cat = cat
            binding.vm = viewModel
        }
    }

    class CustomCatDiffCallback: DiffUtil.ItemCallback<CustomCat>() {
        override fun areItemsTheSame(oldItem: CustomCat, newItem: CustomCat): Boolean {
            return oldItem.catIdx == newItem.catIdx
        }
        override fun areContentsTheSame(oldItem: CustomCat, newItem: CustomCat): Boolean {
            return oldItem == newItem
        }
    }

}