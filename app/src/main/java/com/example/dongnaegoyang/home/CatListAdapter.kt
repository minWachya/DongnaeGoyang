package com.example.dongnaegoyang

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dongnaegoyang.databinding.CatListBinding
import com.example.dongnaegoyang.home.CatList

class CatListAdapter(private val onClick:(CatList) -> Unit) :
    RecyclerView.Adapter<CatListAdapter.ViewHolder>() {

    var items = ArrayList<CatList>()

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
        val item = items[position]
        viewHolder.setItem(item)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = items.size

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

}
