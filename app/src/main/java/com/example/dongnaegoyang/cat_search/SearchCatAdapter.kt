package com.example.dongnaegoyang.cat_search

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.dongnaegoyang.common.CustomCat
import com.example.dongnaegoyang.common.CustomCatArr
import com.example.dongnaegoyang.databinding.CatListBinding
import com.example.dongnaegoyang.home.CatList

class SearchCatAdapter(private var onClick:(CatList) -> Unit) :
    RecyclerView.Adapter<SearchCatAdapter.ViewHolder>(), Filterable {
    var items = ArrayList<CatList>()

    private var unFilteredList = items // 필터 전 리스트
    private var filteredList = items // 필터 중 리스트

    var cat = ArrayList<CustomCat>()  // 고양이 배열

    // 고양이 생김새 배열: 몸집, 코숏, 귀, 꼬리, 수염
    val arrImgSize = CustomCatArr.arrImgSize
    val arrImgFur = CustomCatArr.arrImgFur
    val arrImgEar = CustomCatArr.arrImgEar
    val arrImgTail = CustomCatArr.arrImgTail
    val arrImgWhisker = CustomCatArr.arrImgWhisker

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchCatAdapter.ViewHolder {
        val binding = CatListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: SearchCatAdapter.ViewHolder, position: Int) {
        val item = filteredList[position]
        holder.setItem(item)
    }

    override fun getItemCount() = filteredList.size

    inner class ViewHolder(private val binding: CatListBinding, val onClick:(CatList) -> Unit) : RecyclerView.ViewHolder(binding.root) {
        private var currentCat: CatList? = null

        init {
            itemView.setOnClickListener {
                currentCat?.let{
                    onClick(it)
                }
            }
        }
        fun setItem(item: CatList) {
            currentCat = item

            binding.tvName.text = item.catName
            //binding.itemImgCatFur.setImageResource(arrImgFur[cat.size][cat.fur])
            binding.itemImgCatSize.setImageResource(arrImgSize[item.ear][cat.size])
            /*binding.itemImgCatEar.setImageResource(arrImgEar[cat.fur][cat.ear])
            binding.itemImgCatTail.setImageResource(arrImgTail[cat.fur][cat.tail])
            binding.itemImgCatWhisker.setImageResource(arrImgWhisker[cat.fur][cat.tail])*/
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint.toString()
                filteredList = if (charString.isEmpty()) {
                    unFilteredList
                } else {
                    val filteringList = ArrayList<CatList>()
                    for (item in unFilteredList) {
                        if (item.catName.toLowerCase().contains(constraint.toString().toLowerCase())) {
                            filteringList.add(item)
                        }
                    }
                    filteringList
                }
                val filterResults = FilterResults()
                filterResults.values = filteredList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredList = results?.values as ArrayList<CatList>
                notifyDataSetChanged()
            }
        }
    }
}