package com.example.dongnaegoyang

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.example.dongnaegoyang.databinding.FragmentCatAdd3Binding

private const val TAG = "mmmCatAddFragment3"

private var _binding: FragmentCatAdd3Binding? = null
private val binding get() = _binding!!

// 고양이 추가: 3단계 프레그먼드
class CatAddFragment3 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCatAdd3Binding.inflate(inflater, container, false)
        val view = binding.root

        // TNR 선택 스피너 설정
        val tnrSpinner = binding.tnrSpinner
        val tnrArray = resources.getStringArray(R.array.cat_add3_tnr_array)
        setSpinner(tnrSpinner, tnrArray)

        // 선호 사료 선택 스피너 설정
        val foodSpinner = binding.foodSpinner
        val foodArray = resources.getStringArray(R.array.cat_add3_food_array)
        setSpinner(foodSpinner, foodArray)

        // 고양이 정보 저장
        binding.btnOK2.setOnClickListener {
            Toast.makeText(context, "고양이 등록 성공!(미완)", Toast.LENGTH_SHORT).show()
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // 스피너 설정
    private fun setSpinner(spinner: Spinner, array: Array<String>) {
        val adapter = object : ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_dropdown_item_1line
        ) { override fun getCount(): Int =  super.getCount() - 1 }  // array에서 hint 안 보이게 하기
        adapter.addAll(array.toMutableList())   // 배열 추가
        spinner.adapter = adapter               // 어댑터 달기
        spinner.setSelection(adapter.count)     // 스피너 초기값=hint
    }

}