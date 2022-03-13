package com.example.dongnaegoyang.cat_search

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.dongnaegoyang.databinding.LocationListBinding
import com.example.dongnaegoyang.home.MainActivity


class LocationAdapter(val siList : ArrayList<String>, val guList : ArrayList<String>, val dongList : ArrayList<String>)
    : RecyclerView.Adapter<LocationAdapter.ViewHolder>(), Filterable {
    private var sis : ArrayList<String>? = siList
    private var gus : ArrayList<String>? = guList
    private var dongs : ArrayList<String>? = dongList

    private lateinit var itemClickListener : ItemClickListener

    class ViewHolder(val binding: LocationListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(si: String, gu: String, dong: String) {
            // 뷰 홀더 시, 구, 동
            binding.tvSi.text = si
            binding.tvGu.text = gu
            binding.tvDong.text = dong

            // 뷰 홀더 클릭하면 주소 전달
            binding.layoutLocation.setOnClickListener {
                val intent = Intent(it.context, MainActivity::class.java)
                intent.putExtra("si", si)
                intent.putExtra("gu", gu)
                intent.putExtra("dong", dong)
                it.context.startActivity(intent)
            }
        }
    }

    // 뷰 홀더 어떤 xml로 생성할지 결정
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = LocationListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    // 뷰 홀더에 데이터 바인딩
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current_si = sis?.get(position)
        holder.binding.tvSi.text = current_si
        val current_gu = gus?.get(position)
        holder.binding.tvGu.text = current_gu
        val current_dong = dongs?.get(position)
        holder.binding.tvDong.text = current_dong

        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)
        }
    }

    override fun getItemCount() = dongList.size

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint.toString()
                sis = if(charString.isEmpty()) {
                    siList
                } else {
                    val filteredList = ArrayList<String>()
                    if(siList != null) {
                        for(si_name in siList) {
                            if(si_name.contains(charString))
                                filteredList.add(si_name)
                        }
                    }
                    filteredList
                }
                val filterResults = FilterResults()
                filterResults.values = sis
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, result: FilterResults?) {
                sis = result?.values as ArrayList<String>
                notifyDataSetChanged()
            }
        }
    }
    interface ItemClickListener {
        fun onClick(view: View, position: Int)
    }
    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }
}