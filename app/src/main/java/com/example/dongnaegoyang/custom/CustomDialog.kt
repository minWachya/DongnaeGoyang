package com.example.dongnaegoyang.custom

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.dongnaegoyang.databinding.CustomDialogBinding

// 커스텀 다이얼로그(CatAddFragment3에서 사용됨)
class CustomDialog(val title: String, val content: String) : DialogFragment() {
    private var _binding: CustomDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = CustomDialogBinding.inflate(inflater, container, false)
        val view = binding.root
        // 레이아웃 배경을 투명하게
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        // 제목, 내용 설정
        binding.customTvTitle.text = title
        binding.customTvContent.text = content

        // 취소 버튼
        binding.customTvBtn1.setOnClickListener {
            dismiss()
        }
        // 확인 버튼
        binding.customTvBtn2.setOnClickListener {
            dismiss()
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}