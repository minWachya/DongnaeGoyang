package com.example.dongnaegoyang.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.dongnaegoyang.cat_detail.CatDetailArr
import com.example.dongnaegoyang.databinding.CatListBinding

class CatListAdapter(private var onClick:(CatList) -> Unit) :
    RecyclerView.Adapter<CatListAdapter.ViewHolder>(), Filterable {

    var items = ArrayList<CatList>()

    private var unFilteredList = items // 필터 전 리스트
    private var filteredList = items // 필터 중 리스트

    // 고양이 생김새 배열: 몸집, 코숏, 귀, 꼬리, 수염
    val arrImgSize = CatDetailArr.arrImgSize
    val arrImgFur = CatDetailArr.arrImgFur
    val arrImgEar = CatDetailArr.arrImgEar
    val arrImgTail = CatDetailArr.arrImgTail
    val arrImgWhisker = CatDetailArr.arrImgWhisker

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = CatListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, onClick)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item = filteredList[position]
        viewHolder.setItem(item)
    }

    // Return the size of your dataset (invoked by the layout manager)
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
            binding.itemImgCatFur.setImageResource(arrImgFur[item.size][item.fur])
            binding.itemImgCatSize.setImageResource(arrImgSize[item.size])
            binding.itemImgCatEar.setImageResource(arrImgEar[item.fur][item.ear])
            binding.itemImgCatTail.setImageResource(arrImgTail[item.fur][item.tail])
            binding.itemImgCatWhisker.setImageResource(arrImgWhisker[item.fur][item.tail])
        }
    }

    // 탭 필터링
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint.toString()
                filteredList = if (charString.isEmpty()) {
                    unFilteredList // 필터된 리스트
                } else {
                    val filteringList = ArrayList<CatList>()
                    for (item in unFilteredList!!) {
                        if (item!!.type == charString) filteringList.add(item)
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
