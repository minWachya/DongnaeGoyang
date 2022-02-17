package com.example.dongnaegoyang

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import android.widget.ArrayAdapter
import com.example.dongnaegoyang.databinding.FragmentCatAdd2Binding

private const val TAG = "mmmCatAddFragment2"

private var _binding: FragmentCatAdd2Binding? = null
private val binding get() = _binding!!

// 고양이 추가: 2단계 프레그먼드
class CatAddFragment2 : Fragment() {
    // 이름, 주 출몰지, 특이사항 입력 여부
    var checkName = false
    var checkPlace = false
    var checkNote = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCatAdd2Binding.inflate(inflater, container, false)
        val view = binding.root

        // 이름 입력 확인
        binding.editName.addTextChangedListener {
            checkName = binding.editName.text.trim().toString().isNotEmpty()
            btnEnableCheck()    // 이름, 주 출몰지, 특이사항 모두 입력해야 버튼 활성화
        }
        // 주 출몰지 입력 확인
        binding.editPlace.addTextChangedListener {
            checkPlace = binding.editPlace.text.trim().toString().isNotEmpty()
            btnEnableCheck()    // 이름, 주 출몰지, 특이사항 모두 입력해야 버튼 활성화
        }
        // 특이사항 입력 확인
        binding.editSpecialNote.addTextChangedListener {
            checkNote = binding.editSpecialNote.text.trim().toString().isNotEmpty()
            btnEnableCheck()    // 이름, 주 출몰지, 특이사항 모두 입력해야 버튼 활성화
        }

        // 성별 선택 스피너 설정
        // 배열 추가
        val genderSpinner = binding.genderSipnner
        ArrayAdapter.createFromResource(
            requireActivity().applicationContext,
            R.array.cat_add2_gender_array,
            android.R.layout.simple_dropdown_item_1line
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            genderSpinner.adapter = adapter
        }

        // 추정 나이 선택 스피너 설정
        // 배열 추가
        val ageSpinner = binding.ageSpinner
        ArrayAdapter.createFromResource(
            requireActivity().applicationContext,
            R.array.cat_add2_age_array,
            android.R.layout.simple_dropdown_item_1line
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            ageSpinner.adapter = adapter
        }

        // <이전> 버튼 클릭: 1단계로 이동
        binding.btnBack.setOnClickListener {
            val ft = requireActivity().supportFragmentManager.beginTransaction()
            ft.replace(R.id.catAddFrameLayout, CatAddFragment1()).commit()
        }

        // <다음> 버튼 클릭: 3단계로 이동
        binding.btnOK2.setOnClickListener {
            val ft = requireActivity().supportFragmentManager.beginTransaction()
            ft.replace(R.id.catAddFrameLayout, CatAddFragment3()).commit()
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // 모두 입력 시 버튼 활성화
    private fun btnEnableCheck() {
        binding.btnOK2.isEnabled = checkName && checkPlace && checkNote
    }

}