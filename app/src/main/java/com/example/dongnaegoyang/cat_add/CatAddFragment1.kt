package com.example.dongnaegoyang.cat_add

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.dongnaegoyang.R
import com.example.dongnaegoyang.cat_detail.CatDetailArr
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
        // 고양이 생김새 스피너: 몸집, 코숏, 귀, 꼬리, 수염
        val spinnerCatSize = binding.spinnerCatSize
        val spinnerCatFur = binding.spinnerCatFur
        val spinnerCatEar = binding.spinnerCatEar
        val spinnerCatTail = binding.spinnerCatTail
        val spinnerCatWhisker = binding.spinnerCatWhiskers
        // 고양이 생김새 배열: 몸집, 코숏, 귀, 꼬리, 수염
        val arrImgSize = CatDetailArr.arrImgSize
        val arrImgFur = CatDetailArr.arrImgFur
        val arrImgEar = CatDetailArr.arrImgEar
        val arrImgTail = CatDetailArr.arrImgTail
        val arrImgWhisker = CatDetailArr.arrImgWhisker

        // 몸집 선택: 털 색 번경
        spinnerCatSize.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) =
                binding.imgCatSize.setImageResource(arrImgSize[position])
        }
        setBtnListener(spinnerCatSize.adapter.count-1, binding.imgViewSizeLeft, binding.imgViewSizeRight, spinnerCatSize)
        // 코숏 선택: 털 색에 맞게 귀, 꼬리, 수염 색 변경
        spinnerCatFur.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                binding.imgCatFur.setImageResource(arrImgFur[spinnerCatSize.selectedItemPosition][position])    // 코숏
                binding.imgCatEar.setImageResource(arrImgEar[position][spinnerCatEar.selectedItemPosition])     // 귀
                binding.imgCatTail.setImageResource(arrImgTail[position][spinnerCatTail.selectedItemPosition])  // 꼬리
                binding.imgCatWhisker.setImageResource(arrImgWhisker[position][spinnerCatWhisker.selectedItemPosition])//수염
            }
        }
        setBtnListener(spinnerCatFur.adapter.count-1, binding.imgViewFurLeft, binding.imgViewFurRight, spinnerCatFur)
        // 귀 모양 선택: 털 색에 맞게 귀 모양 변경
        spinnerCatEar.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) =
                binding.imgCatEar.setImageResource(arrImgEar[spinnerCatFur.selectedItemPosition][position])
        }
        setBtnListener(spinnerCatEar.adapter.count-1, binding.imgViewEarLeft, binding.imgViewEarRight, spinnerCatEar)
        // 꼬리 모양 선택: 털 색에 맞게 꼬리 모양 변경
        spinnerCatTail.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) =
                binding.imgCatTail.setImageResource(arrImgTail[spinnerCatFur.selectedItemPosition][position])
        }
        setBtnListener(spinnerCatTail.adapter.count-1, binding.imgViewTailLeft, binding.imgViewTailRight, spinnerCatTail)
        // 수염 선택: 털 색에 맞게 수염 색 변경
        spinnerCatWhisker.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (spinnerCatFur.selectedItemPosition == 1 || spinnerCatFur.selectedItemPosition == 5)
                    binding.imgCatWhisker.setImageResource(arrImgWhisker[1][position])  // 흰 수염
                else binding.imgCatWhisker.setImageResource(arrImgWhisker[0][position]) // 검은 수염
            }
        }
        setBtnListener(spinnerCatWhisker.adapter.count-1, binding.imgViewWhiskersLeft, binding.imgViewWhiskersRight, spinnerCatWhisker)

        val bundle1 = arguments?: Bundle()
        // 이전 선택 정보 보여주기
        setPrevInfo(bundle1)

        // 2단계로 이동
        binding.btnOK1.setOnClickListener {
            setFrag(CatAddFragment2(), bundle1)
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // 고양이 생김새 설정 버튼 리스너 달기
    private fun setBtnListener(max: Int, imageViewLeft: ImageView, imageViewRight: ImageView, spinner: Spinner) {
        var num = 0
        imageViewLeft.setOnClickListener {
            num--
            if (num < 0) num = max
            spinner.setSelection(num)
        }
        imageViewRight.setOnClickListener {
            num++
            if (num > max) num = 0
            spinner.setSelection(num)
        }
    }

    // 이전 정보 보여주기
    private fun setPrevInfo(bundle: Bundle?) {
        if (bundle?.getInt("size") != null) {
            val numSize = bundle.getInt("size") // 몸집
            val numFur = bundle.getInt("fur")   // 코숏
            val numEar = bundle.getInt("ear")   // 귀 모양
            val numTail = bundle.getInt("tail") // 꼬리 모양
            val numWhiskers = bundle.getInt("whiskers")  // 수염
            binding.spinnerCatSize.setSelection(numSize)
            binding.spinnerCatFur.setSelection(numFur)
            binding.spinnerCatEar.setSelection(numEar)
            binding.spinnerCatTail.setSelection(numTail)
            binding.spinnerCatWhiskers.setSelection(numWhiskers)
        }
    }

    // 프레그먼트 이동
    private fun setFrag(catAddFragment: Fragment, bundle: Bundle?) {
        // 1단계 정보 함께 보내기
        if (bundle != null) {
            val numSize = binding.spinnerCatSize.selectedItemPosition       // 몸집
            val numFur = binding.spinnerCatFur.selectedItemPosition         // 코숏
            val numEar = binding.spinnerCatEar.selectedItemPosition         // 귀 모양
            val numTail = binding.spinnerCatTail.selectedItemPosition       // 꼬리 모양
            val numWhiskers = binding.spinnerCatWhiskers.selectedItemPosition   // 수염
            bundle.putInt("size", numSize)  // 몸집
            bundle.putInt("fur", numFur)    // 코숏
            bundle.putInt("ear", numEar)    // 귀 모양
            bundle.putInt("tail", numTail)  // 꼬리 모양
            bundle.putInt("whiskers", numWhiskers)    // 수염
            catAddFragment.arguments = bundle
        }
        // 프레그먼트에 정보 전달 + 이동
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.catAddFrameLayout, catAddFragment).commit()
    }

}