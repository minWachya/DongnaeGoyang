package com.example.dongnaegoyang

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.Html
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.core.text.HtmlCompat
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

        // <이전> 버튼 클릭: 2단계로 이동
        binding.btnBack.setOnClickListener {
            val ft = requireActivity().supportFragmentManager.beginTransaction()
            ft.replace(R.id.catAddFrameLayout, CatAddFragment2()).commit()
        }

        // <등록> 버튼 클릭: 고양이 정보 저장
        binding.btnOK3.setOnClickListener {
            AlertDialog.Builder(context, R.style.CustomAlertDialog)
                .setTitle("등록 확인")
                .setMessage("00구 00동에 새로운 고영희를 등록하시겠습니까?")
                .setPositiveButton("Ok", /* listener = */ null)
                .setNegativeButton("Cancel", /* listener = */ null)
                .show()
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