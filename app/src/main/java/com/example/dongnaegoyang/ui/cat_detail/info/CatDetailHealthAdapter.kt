package com.example.dongnaegoyang.ui.cat_detail.info

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dongnaegoyang.data.local.HealthInfo
import com.example.dongnaegoyang.databinding.ItemHealthInfoBinding

// 고양이 상세 페에지: '정보' 탭 건강 정보 어댑터
class CatDetailHealthAdapter :
    ListAdapter<HealthInfo, CatDetailHealthAdapter.HealthViewHolder>(
        HealthListDiffCallback()
    ) {
    private lateinit var binding: ItemHealthInfoBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HealthViewHolder {
        binding = ItemHealthInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HealthViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HealthViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class HealthViewHolder(private val binding: ItemHealthInfoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(health: HealthInfo) {
            binding.health = health
        }
    }

    class HealthListDiffCallback : DiffUtil.ItemCallback<HealthInfo>() {
        override fun areItemsTheSame(oldItem: HealthInfo, newItem: HealthInfo): Boolean =
            oldItem.content == newItem.content
        override fun areContentsTheSame(oldItem: HealthInfo, newItem: HealthInfo): Boolean =
            oldItem == newItem
    }
}