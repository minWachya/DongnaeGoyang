package com.example.dongnaegoyang.sidebar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dongnaegoyang.databinding.FragmentNoticeBinding

private const val TAG = "mmmNoticeFragment"

private var _binding: FragmentNoticeBinding? = null
private val binding get() = _binding!!

class NoticeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNoticeBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.layoutNoticeTitle.setOnClickListener {
            if (binding.layoutExpand.visibility == View.VISIBLE) {
                binding.layoutExpand.visibility = View.GONE
            } else {
                binding.layoutExpand.visibility = View.VISIBLE
            }
        }

        return view
    }

    companion object {
        const val TAG = "NoticeFragment"
    }
}