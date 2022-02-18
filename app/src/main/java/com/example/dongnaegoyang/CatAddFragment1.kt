package com.example.dongnaegoyang

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
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
        setBtnListener(arrSize, binding.imgViewSizeLeft, binding.imgViewSizeRight, binding.textCatSize)
        // 코숏 선택
        val arrFur = resources.getStringArray(R.array.cat_add1_fur_array)
        setBtnListener(arrFur, binding.imgViewFurLeft, binding.imgViewFurRight, binding.textFur)
        // 귀 모양 선택
        val arrEar = resources.getStringArray(R.array.cat_add1_ear_array)
        setBtnListener(arrEar, binding.imgViewEarLeft, binding.imgViewEarRight, binding.textEar)
        // 꼬리 모양 선택
        val arrTail = resources.getStringArray(R.array.cat_add1_tail_array)
        setBtnListener(arrTail, binding.imgViewTailLeft, binding.imgViewTailRight, binding.textTail)
        // 수염 선택
        val arrWhiskers = resources.getStringArray(R.array.cat_add1_whiskers_array)
        setBtnListener(arrWhiskers, binding.imgViewWhiskersLeft, binding.imgViewWhiskersRight, binding.textWhiskers)

        // 고양이 선택 Text 초기화
        val bundle = arguments
        // 2단계에서 1단계로 되돌아온 거면 이전 선택 정보 보여주기
        if (bundle != null) {
            val numSize = bundle.getInt("size") // 몸집
            val numFur = bundle.getInt("fur")   // 코숏
            val numEar = bundle.getInt("ear")   // 귀 모양
            val numTail = bundle.getInt("tail") // 꼬리 모양
            val numWhiskers = bundle.getInt("whiskers")  // 수염
            binding.textCatSize.text = arrSize[numSize]
            binding.textFur.text = arrFur[numFur]
            binding.textEar.text = arrEar[numEar]
            binding.textTail.text = arrTail[numTail]
            binding.textWhiskers.text = arrWhiskers[numWhiskers]
        }
        // 아니면 그냥 기본 선택 정보 보여주기
        else {
            binding.textCatSize.text = arrSize[0]
            binding.textFur.text = arrFur[0]
            binding.textEar.text = arrEar[0]
            binding.textTail.text = arrTail[0]
            binding.textWhiskers.text = arrWhiskers[0]
        }

        // 2단계로 이동
        binding.btnOK1.setOnClickListener {
            // 정보 전달
            val numSize = arrSize.indexOf(binding.textCatSize.text) // 몸집
            val numFur = arrFur.indexOf(binding.textFur.text)       // 코숏
            val numEar = arrEar.indexOf(binding.textEar.text)       // 귀 모양
            val numTail = arrTail.indexOf(binding.textTail.text)    // 꼬리 모양
            val numWhiskers = arrWhiskers.indexOf(binding.textWhiskers.text) // 수염
            val bundle1 = Bundle()
            bundle1.putInt("size", numSize)  // 몸집
            bundle1.putInt("fur", numFur)    // 코숏
            bundle1.putInt("ear", numEar)    // 귀 모양
            bundle1.putInt("tail", numTail)  // 꼬리 모양
            bundle1.putInt("whiskers", numWhiskers)    // 수염

            // 다음 프레그먼트에 전달
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            val catAddFragment2 = CatAddFragment2()
            catAddFragment2.arguments = bundle1
            transaction.replace(R.id.catAddFrameLayout, catAddFragment2)
            transaction.commit()
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // 고양이 생김새 설정 버튼 리스너 달기
    private fun setBtnListener(arr: Array<String>, imageViewLeft: ImageView, imageViewRight: ImageView, text: TextView) {
        val max = arr.size-1
        var num = 0

        imageViewLeft.setOnClickListener {
            num--
            if (num < 0) num = max
            text.text = arr[num]
        }
        imageViewRight.setOnClickListener {
            num++
            if (num > max) num = 0
            text.text = arr[num]
        }
    }

}