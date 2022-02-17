package com.example.dongnaegoyang

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.dongnaegoyang.databinding.FragmentCatAdd1Binding

private const val TAG = "mmmCatAddFragment1"

private var _binding: FragmentCatAdd1Binding? = null
private val binding get() = _binding!!

// 고양이 추가: 1단계 프레그먼드
class CatAddFragment1 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCatAdd1Binding.inflate(inflater, container, false)
        val view = binding.root

        // 몸집 선택
        val arrSize = resources.getStringArray(R.array.cat_add1_size_array)
        setBtnListener(arrSize, binding.btnSizeLeft, binding.btnSizeRight, binding.textCatSize)
        // 코숏 선택
        val arrFur = resources.getStringArray(R.array.cat_add1_fur_array)
        setBtnListener(arrFur, binding.btnFurLeft, binding.btnFurRight, binding.textFur)
        // 귀 모양 선택
        val arrEar = resources.getStringArray(R.array.cat_add1_ear_array)
        setBtnListener(arrEar, binding.btnEarLeft, binding.btnEarRight, binding.textEar)
        // 꼬리 모양 선택
        val arrTail = resources.getStringArray(R.array.cat_add1_tail_array)
        setBtnListener(arrTail, binding.btnTailLeft, binding.btnTailRight, binding.textTail)
        // 수염 선택
        val arrWhiskers = resources.getStringArray(R.array.cat_add1_whiskers_array)
        setBtnListener(arrWhiskers, binding.btnWhiskersLeft, binding.btnWhiskersRight, binding.textWhiskers)

        // 2단계로 이동
        binding.btnOK1.setOnClickListener {
            val ft = requireActivity().supportFragmentManager.beginTransaction()
            ft.replace(R.id.catAddFrameLayout, CatAddFragment2()).commit()
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // 고양이 생김새 설정 버튼 리스너 달기
    private fun setBtnListener(arr: Array<String>, btnLeft: Button, btnRight: Button, text: TextView) {
        val max = arr.size-1
        var num = 0

        btnLeft.setOnClickListener {
            num--
            if (num < 0) num = max
            text.text = arr[num]
        }
        btnRight.setOnClickListener {
            num++
            if (num > max) num = 0
            text.text = arr[num]
        }
    }

}