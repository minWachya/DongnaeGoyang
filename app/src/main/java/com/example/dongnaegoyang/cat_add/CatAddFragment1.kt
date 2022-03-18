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
        // 고양이 생김새 배열: 몸집, 코숏, 귀, 꼬리, 수염        // !! 기본 이미지는 배열의 맨 마지막 아이템임!!
        val arrImgSize = CatDetailArr.arrImgSize
        val arrImgFur = CatDetailArr.arrImgFur
        val arrImgEar = CatDetailArr.arrImgEar
        val arrImgTail = CatDetailArr.arrImgTail
        val arrImgWhisker = CatDetailArr.arrImgWhisker
        // 고양이 생김새 스피너 배열 최대 갤이                   // !! hint는 배열의 맨 마지막 아이템임!!
        val maxSize = spinnerCatSize.adapter.count-1
        val maxFur = spinnerCatFur.adapter.count-1
        val maxEar = spinnerCatEar.adapter.count-1
        val maxTail = spinnerCatTail.adapter.count-1
        val maxWhisker = spinnerCatWhisker.adapter.count-1

        // 고양이 커스텀: 왼쪽, 오른쪽 화살표 버튼 리스너: 기본 position 위해 -1, 맨 마지막에 있는 hint 제외하기 위헤 -1 = -2
        setBtnListener(spinnerCatSize.adapter.count-2, binding.imgViewSizeLeft, binding.imgViewSizeRight, spinnerCatSize)
        setBtnListener(spinnerCatFur.adapter.count-2, binding.imgViewFurLeft, binding.imgViewFurRight, spinnerCatFur)
        setBtnListener(spinnerCatEar.adapter.count-2, binding.imgViewEarLeft, binding.imgViewEarRight, spinnerCatEar)
        setBtnListener(spinnerCatTail.adapter.count-2, binding.imgViewTailLeft, binding.imgViewTailRight, spinnerCatTail)
        setBtnListener(spinnerCatWhisker.adapter.count-2, binding.imgViewWhiskersLeft, binding.imgViewWhiskersRight, spinnerCatWhisker)

        // 몸집 선택: 몸집에 맞게 털 색 번경
        spinnerCatSize.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position == maxSize) return      // hint 넘기기
                binding.imgCatSize.setImageResource(arrImgSize[position])       // 몸집
                binding.imgCatFur.setImageResource(arrImgFur[position][spinnerCatFur.selectedItemPosition]) // 코숏
                btnEnableCheck()
            }
        }
        // 코숏 선택: 털 색에 맞게 귀, 꼬리, 수염 색 변경
        spinnerCatFur.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position == maxFur) return      // hint 넘기기
                binding.imgCatFur.setImageResource(arrImgFur[spinnerCatSize.selectedItemPosition][position])    // 코숏
                // 귀 미선택 시 코숏 색에 맞춘 귀 이미지로 변경
                val earIndex = if(spinnerCatEar.selectedItemPosition == maxEar) 0 else spinnerCatEar.selectedItemPosition
                binding.imgCatEar.setImageResource(arrImgEar[position][earIndex])     // 귀
                // 꼬리 미선택 시 코숏 색에 맞춘 꼬리 이미지로 변경
                val tailIndex = if(spinnerCatTail.selectedItemPosition == maxTail) 0 else spinnerCatTail.selectedItemPosition
                binding.imgCatTail.setImageResource(arrImgTail[position][tailIndex])  // 꼬리
                // 수염 미선택 시 코숏 색에 맞춘 수염 이미지로 변경
                val whiskerIndex = if(spinnerCatWhisker.selectedItemPosition == maxWhisker) 0 else spinnerCatWhisker.selectedItemPosition
                binding.imgCatWhisker.setImageResource(arrImgWhisker[position][whiskerIndex])//수염
                btnEnableCheck()
            }
        }
        // 귀 모양 선택: 털 색에 맞게 귀 모양 변경
        spinnerCatEar.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position == maxEar) return      // hint 넘기기
                binding.imgCatEar.setImageResource(arrImgEar[spinnerCatFur.selectedItemPosition][position])
                btnEnableCheck()
            }
        }
        // 꼬리 모양 선택: 털 색에 맞게 꼬리 모양 변경
        spinnerCatTail.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position == maxTail) return      // hint 넘기기
                binding.imgCatTail.setImageResource(arrImgTail[spinnerCatFur.selectedItemPosition][position])
                btnEnableCheck()
            }
        }
        // 수염 선택: 털 색에 맞게 수염 색 변경
        spinnerCatWhisker.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position == maxWhisker) return      // hint 넘기기
                binding.imgCatWhisker.setImageResource(arrImgWhisker[spinnerCatFur.selectedItemPosition][position])
                btnEnableCheck()
            }
        }

        // 번들 없으면 생성, 있으면 기존 거 그대로 사용
        val bundle1 = if (arguments == null) {
            // 기본 선택 보이기
            setDefaultCatInfo()
            Bundle()
        } else {
            // 이전 선택 정보 보이기
            setPrevInfo(arguments as Bundle)
            arguments
        }


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
        var num = max+1
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

    // 모두 입력 시 버튼 활성화
    private fun btnEnableCheck() {
        // 고양이 생김새 스피너: 몸집, 코숏, 귀, 꼬리, 수염
        val currentCatSize = binding.spinnerCatSize.selectedItemPosition
        val currentCatFur = binding.spinnerCatFur.selectedItemPosition
        val currentCatEar = binding.spinnerCatEar.selectedItemPosition
        val currentCatTail = binding.spinnerCatTail.selectedItemPosition
        val currentCatWhisker = binding.spinnerCatWhiskers.selectedItemPosition
        // 고양이 생김새 스피너 배열 최대 길이(힌트 position): 몸집, 코숏, 귀, 꼬리, 수염
        val maxCatSize = binding.spinnerCatSize.adapter.count - 1
        val maxCatFur = binding.spinnerCatFur.adapter.count - 1
        val maxCatEar = binding.spinnerCatEar.adapter.count - 1
        val maxCatTail = binding.spinnerCatTail.adapter.count - 1
        val maxCatWhisker = binding.spinnerCatWhiskers.adapter.count - 1

        binding.btnOK1.isEnabled = currentCatSize != maxCatSize && currentCatFur != maxCatFur &&
                currentCatEar != maxCatEar && currentCatTail != maxCatTail && currentCatWhisker != maxCatWhisker
    }

    // 이전 정보 보여주기
    private fun setPrevInfo(bundle: Bundle) {
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
    // 스피너 초기 설정: 배열의 맨 마지막인 hint 보이기
    private fun setDefaultCatInfo() {
        binding.spinnerCatSize.setSelection(binding.spinnerCatSize.adapter.count-1)
        binding.spinnerCatFur.setSelection(binding.spinnerCatFur.adapter.count-1)
        binding.spinnerCatEar.setSelection(binding.spinnerCatEar.adapter.count-1)
        binding.spinnerCatTail.setSelection(binding.spinnerCatTail.adapter.count-1)
        binding.spinnerCatWhiskers.setSelection(binding.spinnerCatWhiskers.adapter.count-1)
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