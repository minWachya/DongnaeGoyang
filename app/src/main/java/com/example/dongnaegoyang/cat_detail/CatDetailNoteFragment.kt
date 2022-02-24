package com.example.dongnaegoyang.cat_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dongnaegoyang.R
import com.example.dongnaegoyang.databinding.FragmentCatDetailNoteBinding

private const val TAG = "mmmCatDetailNoteFragment"
private var _binding: FragmentCatDetailNoteBinding? = null
private val binding get() = _binding!!

// 고양이 상세 페이지: '오늘 기록' 탭
class CatDetailNoteFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCatDetailNoteBinding.inflate(inflater, container, false)
        val view = binding.root


        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}