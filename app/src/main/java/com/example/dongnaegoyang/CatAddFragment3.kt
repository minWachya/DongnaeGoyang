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
import android.widget.*
import androidx.core.text.HtmlCompat
import com.example.dongnaegoyang.databinding.FragmentCatAdd3Binding
import com.google.android.material.bottomsheet.BottomSheetDialog

private const val TAG = "mmmCatAddFragment3"

private var _binding: FragmentCatAdd3Binding? = null
private val binding get() = _binding!!

// 고양이 추가: 3단계 프레그먼드
class CatAddFragment3 : Fragment() {
    // BottomDialog 위한 spinner_custom_layout.xml 아이디
    val arrTextViewId = arrayListOf(R.id.title, R.id.text1, R.id.text2, R.id.text3, R.id.text4, R.id.text5, R.id.text6)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCatAdd3Binding.inflate(inflater, container, false)
        val view = binding.root

        // TNR 선택 스피너 설정
        val tnrBottomSheetView = layoutInflater.inflate(R.layout.spinner_custom_layout, null)
        val tnrBottomSheetDialog = BottomSheetDialog(requireContext())
        val tnrArray = resources.getStringArray(R.array.cat_add3_tnr_array)
        tnrBottomSheetDialog.setContentView(tnrBottomSheetView)
        setBottomSheetView(tnrBottomSheetView, tnrArray, tnrBottomSheetDialog, binding.tnrSpinner)
        binding.tnrSpinner.setOnClickListener {
            tnrBottomSheetDialog.show()
        }

        // 선호 사료 선택 스피너 설정
        val foodBottomSheetView = layoutInflater.inflate(R.layout.spinner_custom_layout, null)
        val foodBottomSheetDialog = BottomSheetDialog(requireContext())
        val foodArray = resources.getStringArray(R.array.cat_add3_food_array)
        foodBottomSheetDialog.setContentView(foodBottomSheetView)
        setBottomSheetView(foodBottomSheetView, foodArray, foodBottomSheetDialog, binding.foodSpinner)
        binding.foodSpinner.setOnClickListener {
            foodBottomSheetDialog.show()
        }

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

    // 스피너 선택 설정
    private fun setBottomSheetView(bottomSheetView: View, arr: Array<String>, dialog: BottomSheetDialog, spinner: TextView) {
        for (i in arr.indices) {
            val textView = bottomSheetView.findViewById<TextView>(arrTextViewId[i])
            textView.text = arr[i]
            textView.visibility = View.VISIBLE
            textView.setOnClickListener {
                spinner.text = arr[i]
                dialog.dismiss()
            }
        }
        // X버튼 누르면 닫힘
        val closeButton = bottomSheetView.findViewById<ImageView>(R.id.imgClose)
        closeButton.setOnClickListener {
            dialog.dismiss()
        }
    }

}