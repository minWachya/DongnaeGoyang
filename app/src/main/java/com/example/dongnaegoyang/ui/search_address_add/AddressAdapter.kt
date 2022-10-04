package com.example.dongnaegoyang.ui.search_address_add

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dongnaegoyang.data.local.Address
import com.example.dongnaegoyang.databinding.ItemAddressAddBinding

class AddressAdapter(val context: Context) :
    ListAdapter<Address, AddressAdapter.AddressViewHolder>(AddressDiffCallback()) {
    private lateinit var binding: ItemAddressAddBinding

    // Adapter 값 리턴하기 위한 인터페이스 객체
    private lateinit var mCallback: SelectAddressInterface

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressViewHolder {
        mCallback = context as SelectAddressInterface

        binding = ItemAddressAddBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AddressViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AddressViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class AddressViewHolder(private val binding: ItemAddressAddBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(address: Address) {
            binding.address = address
            binding.executePendingBindings()

            binding.constraintLayout.setOnClickListener {
                // 리턴할 값 넣기!!
                mCallback.onSelectedAddress(address.addressInfo.address1,
                    address.addressInfo.address2,
                    address.addressInfo.address3)
            }
        }
    }

}

class AddressDiffCallback : DiffUtil.ItemCallback<Address>() {
    override fun areItemsTheSame(oldItem: Address, newItem: Address): Boolean {
        return oldItem.addressName == newItem.addressName
    }

    override fun areContentsTheSame(oldItem: Address, newItem: Address): Boolean {
        return oldItem == newItem
    }
}
