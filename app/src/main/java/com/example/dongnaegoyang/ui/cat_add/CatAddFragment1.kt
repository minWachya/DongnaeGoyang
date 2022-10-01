package com.example.dongnaegoyang.ui.cat_add

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.example.dongnaegoyang.R
// 고양이 생김새 배열: 몸집, 코숏, 귀, 꼬리, 수염        // !! 기본 이미지는 배열의 맨 마지막 아이템임!!
import com.example.dongnaegoyang.common.CustomCatArr.arrImgEar
import com.example.dongnaegoyang.common.CustomCatArr.arrImgFur
import com.example.dongnaegoyang.common.CustomCatArr.arrImgSize
import com.example.dongnaegoyang.common.CustomCatArr.arrImgTail
import com.example.dongnaegoyang.common.CustomCatArr.arrImgWhisker
import com.example.dongnaegoyang.databinding.FragmentCatAdd1Binding
import com.example.dongnaegoyang.ui.base.BaseFragment

private const val TAG = "mmmCatAddFragment1"

// 고양이 추가: 1단계 프레그먼드
class CatAddFragment1 : BaseFragment<FragmentCatAdd1Binding>(R.layout.fragment_cat_add1) {
    // 고양이 생김새 스피너 배열 최대 길이, !! hint는 배열의 맨 마지막 아이템임!!
    var maxSize: Int = 0
    var maxFur: Int = 0
    var maxEar: Int = 0
    var maxTail: Int = 0
    var maxWhisker: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner

        setToolbar()
        setSpinnerMaxSize()
        setBtnListeners()   // ◀, ▶화살표 버튼 리스너 달기

        // 스피너 선택 리스너 달기
        setSpinnerCatListener()         // 몸집
        setSpinnerCatFurListener()      // 코숏
        setSpinnerCatEarListener()      // 귀
        setSpinnerCatTailListener()     // 꼬리
        setSpinnerCatWhiskerListener()  // 수염

        // 이전에 선택했던 정보 있으면 보이기
        isArgumentsExist()

        // 2단계로 이동
        binding.btnOK1.setOnClickListener {
            setFrag(CatAddFragment2(), arguments ?: Bundle())
        }
    }

    private fun setSpinnerMaxSize() {
        maxSize = binding.spinnerCatSize.adapter.count-1
        maxFur = binding.spinnerCatFur.adapter.count-1
        maxEar = binding.spinnerCatEar.adapter.count-1
        maxTail = binding.spinnerCatTail.adapter.count-1
        maxWhisker = binding.spinnerCatWhiskers.adapter.count-1
    }

    // 툴바 달기
    private fun setToolbar() {
        (activity as CatAddActivity).setSupportActionBar(binding.toolBar)
        (activity as CatAddActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    //  ◀, ▶화살표 버튼 리스너 달기
    private fun setBtnListeners() {
        // 고양이 커스텀: 왼쪽, 오른쪽 화살표 버튼 리스너: 기본 position 위해 -1, 맨 마지막에 있는 hint 제외하기 위헤 -1 = -2
        setBtnListener(binding.spinnerCatSize.adapter.count-2,
            binding.imgViewSizeLeft, binding.imgViewSizeRight, binding.spinnerCatSize)
        setBtnListener(binding.spinnerCatFur.adapter.count-2,
            binding.imgViewFurLeft, binding.imgViewFurRight, binding.spinnerCatFur)
        setBtnListener(binding.spinnerCatEar.adapter.count-2,
            binding.imgViewEarLeft, binding.imgViewEarRight, binding.spinnerCatEar)
        setBtnListener(binding.spinnerCatTail.adapter.count-2,
            binding.imgViewTailLeft, binding.imgViewTailRight, binding.spinnerCatTail)
        setBtnListener(binding.spinnerCatWhiskers.adapter.count-2,
            binding.imgViewWhiskersLeft, binding.imgViewWhiskersRight, binding.spinnerCatWhiskers)
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

    // 몸집 선택: arrImgSize[ear][size]: 몸집에 맞게 털 색 번경
    private fun setSpinnerCatListener() {
        binding.spinnerCatSize.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position == maxSize) return      // hint 넘기기
                // 귀 미선택 시 코숏 색에 맞춘 귀 이미지로 변경
                val earIndex = if(binding.spinnerCatEar.selectedItemPosition == maxEar) 0 else binding.spinnerCatEar.selectedItemPosition
                binding.imgCatSize.setImageResource(arrImgSize[earIndex][position])       // 몸집
                binding.imgCatFur.setImageResource(arrImgFur[position][binding.spinnerCatFur.selectedItemPosition]) // 코숏
                btnEnableCheck()
            }
        }
    }

    // 코숏 선택: arrImgFur[size][fur]: 털 색에 맞게 귀, 꼬리, 수염 색 변경
    private fun setSpinnerCatFurListener() {
        binding.spinnerCatFur.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position == maxFur) return      // hint 넘기기
                binding.imgCatFur.setImageResource(arrImgFur[binding.spinnerCatSize.selectedItemPosition][position])    // 코숏
                // 귀 미선택 시 코숏 색에 맞춘 귀 이미지로 변경
                val earIndex = if(binding.spinnerCatEar.selectedItemPosition == maxEar) 0
                                else binding.spinnerCatEar.selectedItemPosition
                binding.imgCatEar.setImageResource(arrImgEar[position][earIndex])     // 귀
                // 꼬리 미선택 시 코숏 색에 맞춘 꼬리 이미지로 변경
                val tailIndex = if(binding.spinnerCatTail.selectedItemPosition == maxTail) 0
                                else binding.spinnerCatTail.selectedItemPosition
                binding.imgCatTail.setImageResource(arrImgTail[position][tailIndex])  // 꼬리
                // 수염 미선택 시 코숏 색에 맞춘 수염 이미지로 변경
                val whiskerIndex = if(binding.spinnerCatWhiskers.selectedItemPosition == maxWhisker) 0
                                    else binding.spinnerCatWhiskers.selectedItemPosition
                binding.imgCatWhisker.setImageResource(arrImgWhisker[position][whiskerIndex])//수염
                btnEnableCheck()
            }
        }
    }

    // 귀 모양 선택: arrImgEar[fur][ear]: 털 색에 맞게 귀 모양 변경 + 귀 모양에 맞게 몸집 모양 변경
    private fun setSpinnerCatEarListener() {
        binding.spinnerCatEar.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position == maxEar) return      // hint 넘기기
                binding.imgCatEar.setImageResource(arrImgEar[binding.spinnerCatFur.selectedItemPosition][position]) // 귀
                // 몸집 미선택 시 기본 몸집 이미지로 변경
                val sizeIndex = if(binding.spinnerCatSize.selectedItemPosition == maxSize) 1
                                else binding.spinnerCatSize.selectedItemPosition
                binding.imgCatSize.setImageResource(arrImgSize[position][sizeIndex])       // 몸집
                btnEnableCheck()
            }
        }
    }

    // 꼬리 모양 선택: arrImgTail[fur][tail]: 털 색에 맞게 꼬리 모양 변경
    private fun setSpinnerCatTailListener() {
        binding.spinnerCatTail.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position == maxTail) return      // hint 넘기기
                binding.imgCatTail.setImageResource(arrImgTail[binding.spinnerCatFur.selectedItemPosition][position])
                btnEnableCheck()
            }
        }
    }

    // 수염 선택: arrImgWhisker[fur][whisker]: 털 색에 맞게 수염 색 변경
    private fun setSpinnerCatWhiskerListener() {
        binding.spinnerCatWhiskers.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position == maxWhisker) return      // hint 넘기기
                binding.imgCatWhisker.setImageResource(arrImgWhisker[binding.spinnerCatFur.selectedItemPosition][position])
                btnEnableCheck()
            }
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

    // 이전에 선택했던 정보 있으면 보이기
    private fun isArgumentsExist() {
        // 기본 선택 보이기
        if (arguments == null) setDefaultCatInfo()
        // 이전 선택 정보 보이기
        else setPrevInfo(arguments as Bundle)
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