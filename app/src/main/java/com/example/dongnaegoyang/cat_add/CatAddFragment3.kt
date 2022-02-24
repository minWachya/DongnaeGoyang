package com.example.dongnaegoyang.cat_add

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.dongnaegoyang.R
import com.example.dongnaegoyang.databinding.FragmentCatAdd3Binding
import com.google.android.material.bottomsheet.BottomSheetDialog

private const val TAG = "mmmCatAddFragment3"

private var _binding: FragmentCatAdd3Binding? = null
private val binding get() = _binding!!

// 고양이 추가: 3단계 프레그먼드
class CatAddFragment3 : Fragment() {
    // BottomDialog 위한 spinner_custom_layout.xml 아이디
    private val arrTextViewId = arrayListOf(R.id.title, R.id.text1, R.id.text2, R.id.text3, R.id.text4, R.id.text5, R.id.text6)

    // TNR, 선호 사료 배열
    private lateinit var tnrArray: Array<String>
    private lateinit var foodArray: Array<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCatAdd3Binding.inflate(inflater, container, false)
        val view = binding.root

        // TNR 선택 스피너 설정
        val tnrBottomSheetView = layoutInflater.inflate(R.layout.spinner_custom_layout, null)
        val tnrBottomSheetDialog = BottomSheetDialog(requireContext(), R.style.DialogCustomTheme)
        tnrArray = resources.getStringArray(R.array.cat_add3_tnr_array)
        tnrBottomSheetDialog.setContentView(tnrBottomSheetView)
        setBottomSheetView(tnrBottomSheetView, tnrArray, tnrBottomSheetDialog, binding.tnrSpinner)
        binding.tnrSpinner.setOnClickListener {
            tnrBottomSheetDialog.show()
        }

        // 선호 사료 선택 스피너 설정
        val foodBottomSheetView = layoutInflater.inflate(R.layout.spinner_custom_layout, null)
        val foodBottomSheetDialog = BottomSheetDialog(requireContext(), R.style.DialogCustomTheme)
        foodArray = resources.getStringArray(R.array.cat_add3_food_array)
        foodBottomSheetDialog.setContentView(foodBottomSheetView)
        setBottomSheetView(foodBottomSheetView, foodArray, foodBottomSheetDialog, binding.foodSpinner)
        binding.foodSpinner.setOnClickListener {
            foodBottomSheetDialog.show()
        }

        val bundle1 = arguments
        // 이전 입력 정보 보여주기
        if (bundle1?.getInt("tnr") != null) {
            val tnr = bundle1.getInt("tnr", -1)    // TNR
            val food = bundle1.getInt("food", -1)  // 선호 사료
            if(tnr != -1) binding.tnrSpinner.text = tnrArray[tnr]
            if(food != -1) binding.foodSpinner.text = foodArray[food]
            Log.d(TAG, "$tnr, $food")
        }
        else {Log.d(TAG, "ㅇ없다")}

        // <이전> 버튼 클릭: 2단계로 이동
        binding.btnBack.setOnClickListener {
            setFrag(CatAddFragment2(), bundle1)
        }

        // <등록> 버튼 클릭: 고양이 정보 저장
        binding.btnOK3.setOnClickListener {
            AlertDialog.Builder(context, R.style.CustomAlertDialog)
                .setTitle("등록 확인")
                .setMessage("00구 00동에 새로운 고영희를 등록하시겠습니까?")
                .setPositiveButton("Ok", null)
                .setNegativeButton("Cancel", null)
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

    // 프레그먼트 이동
    private fun setFrag(catAddFragment: Fragment, bundle: Bundle?) {
        // 3단계 정보 함께 보내기
        if (bundle != null) {
            val tnr = tnrArray.indexOf(binding.tnrSpinner.text)     // TNR
            val food = foodArray.indexOf(binding.foodSpinner.text)  // 선호 사료
            bundle.putInt("tnr", tnr)
            bundle.putInt("food", food)
            catAddFragment.arguments = bundle
        }
        // 프레그먼트에 정보 전달 + 이동
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.catAddFrameLayout, catAddFragment).commit()
    }

}