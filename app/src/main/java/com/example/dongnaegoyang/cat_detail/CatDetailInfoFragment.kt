package com.example.dongnaegoyang.cat_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dongnaegoyang.R
import com.example.dongnaegoyang.databinding.FragmentCatDetailInfoBinding


private const val TAG = "mmmCatDetailInfoFragment"
private var _binding: FragmentCatDetailInfoBinding? = null
private val binding get() = _binding!!

// 고양이 상세 페이지: '정보' 탭
class CatDetailInfoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?)
    : View {
        _binding = FragmentCatDetailInfoBinding.inflate(inflater, container, false)
        val view = binding.root


        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}