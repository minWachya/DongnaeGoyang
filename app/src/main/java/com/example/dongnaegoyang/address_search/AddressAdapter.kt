package com.example.dongnaegoyang.address_search

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.dongnaegoyang.R
import com.example.dongnaegoyang.home.MainActivity

class AddressAdapter(val itemList: ArrayList<AddressList>): RecyclerView.Adapter<AddressAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_address, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: AddressAdapter.ViewHolder, position: Int) {
        holder.si.text = itemList[position].si
        holder.gu.text = itemList[position].gu
        holder.dong.text = itemList[position].dong
        // 아이템 클릭 이벤트
        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)
            val intent = Intent(holder.itemView?.context, MainActivity::class.java)
            intent.putExtra("gu", itemList[position].gu)
            intent.putExtra("dong", itemList[position].dong)
            ContextCompat.startActivity(holder.itemView.context, intent, null)
        }
    }
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val si: TextView = itemView.findViewById(R.id.tv_si)
        val gu: TextView = itemView.findViewById(R.id.tv_gu)
        val dong: TextView = itemView.findViewById(R.id.tv_dong)
    }

    interface OnItemClickListener {
        fun onClick(v: View, position: Int)
    }

    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }

    private lateinit var itemClickListener : OnItemClickListener
}