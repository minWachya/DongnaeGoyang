package com.example.dongnaegoyang.address_search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dongnaegoyang.R
import com.example.dongnaegoyang.cat_search.LocationAdapter
import com.example.dongnaegoyang.databinding.ActivitySearchAddressBinding
import com.example.dongnaegoyang.databinding.FragmentSearchAddressBinding


private const val TAG = "mmmCatSearchFragment"

private var _binding: FragmentSearchAddressBinding? = null
private val binding get() = _binding!!

class SearchAddressFragment : Fragment() {
    private lateinit var binding: ActivitySearchAddressBinding
    private lateinit var adapter: LocationAdapter
    lateinit var siList : ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchAddressBinding.inflate(layoutInflater)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 검색 텍스트박스 변화 감지
        _binding?.tvAddress?.addTextChangedListener(object : TextWatcher {
            // 입력 끝날 때 작동
            override fun afterTextChanged(s: Editable?) {}
            // 입력 전 작동
            override fun beforeTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            // 텍스트에 변화 있으면 작동
            override fun onTextChanged(charSequence: CharSequence?, p1: Int, p2: Int, p3: Int) {
                adapter?.filter?.filter(charSequence)
            }
        })
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_address, container, false)
    }
}