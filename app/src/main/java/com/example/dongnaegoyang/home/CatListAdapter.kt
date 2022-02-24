package com.example.dongnaegoyang.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.dongnaegoyang.databinding.CatListBinding
import com.example.dongnaegoyang.home.CatList

class CatListAdapter(private val onClick:(CatList) -> Unit) :
    RecyclerView.Adapter<CatListAdapter.ViewHolder>(), Filterable {

    var items = ArrayList<CatList>()

    private var context: Context? = null
    private var unFilteredList = items // 필터 전 리스트
    private var filteredList = items // 필터 중 리스트

    // Create new views (invoked by the layout manager)
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
            binding.ivProfile.setImageResource(item.catPic)

            // catPic이 비었을 때 코드
            /*if(item.catPic != null) {
                imgProfile.setImageResource(item.catPic)
            } else {
                imgProfile.setImageResource(R.drawable.catprofile)
            }*/
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
