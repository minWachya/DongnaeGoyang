package com.example.dongnaegoyang

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import android.widget.ImageView
import android.widget.TextView
import com.example.dongnaegoyang.databinding.FragmentCatAdd2Binding
import com.google.android.material.bottomsheet.BottomSheetDialog

private const val TAG = "mmmCatAddFragment2"

private var _binding: FragmentCatAdd2Binding? = null
private val binding get() = _binding!!

// 고양이 추가: 2단계 프레그먼드
class CatAddFragment2 : Fragment() {
    // 이름, 주 출몰지, 특이사항 입력 여부
    var checkName = false
    var checkPlace = false
    var checkNote = false

    // BottomDialog 위한 spinner_custom_layout.xml 아이디
    val arrTextViewId = arrayListOf(R.id.title, R.id.text1, R.id.text2, R.id.text3, R.id.text4, R.id.text5, R.id.text6)

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
        val genderBottomSheetView = layoutInflater.inflate(R.layout.spinner_custom_layout, null)
        val genderBottomSheetDialog = BottomSheetDialog(requireContext(), R.style.DialogCustomTheme)
        val genderArray = resources.getStringArray(R.array.cat_add2_gender_array)
        genderBottomSheetDialog.setContentView(genderBottomSheetView)
        setBottomSheetView(genderBottomSheetView, genderArray, genderBottomSheetDialog, binding.genderSpinner)
        binding.genderSpinner.setOnClickListener {
            genderBottomSheetDialog.show()
        }

        // 추정 나이 선택 스피너 설정
        val ageBottomSheetView = layoutInflater.inflate(R.layout.spinner_custom_layout, null)
        val ageBottomSheetDialog = BottomSheetDialog(requireContext(), R.style.DialogCustomTheme)
        val ageArray = resources.getStringArray(R.array.cat_add2_age_array)
        ageBottomSheetDialog.setContentView(ageBottomSheetView)
        setBottomSheetView(ageBottomSheetView, ageArray, ageBottomSheetDialog, binding.ageSpinner)
        binding.ageSpinner.setOnClickListener {
            ageBottomSheetDialog.show()
        }

        // <이전> 버튼 클릭: 1단계로 이동
        binding.btnBack.setOnClickListener {
            // 1단계에서 작성했던 정보 전달
            val bundle1 = arguments // 1단계에서 넘겨받은 정보
            // 이전 프레그먼트에 전달
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            val catAddFragment1 = CatAddFragment1()
            catAddFragment1.arguments = bundle1
            transaction.replace(R.id.catAddFrameLayout, catAddFragment1).commit()
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
                && binding.genderSpinner.text.isNotEmpty() && binding.ageSpinner.text.isNotEmpty()
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
                btnEnableCheck()
            }
        }
        // X버튼 누르면 닫힘
        val closeButton = bottomSheetView.findViewById<ImageView>(R.id.imgClose)
        closeButton.setOnClickListener {
            dialog.dismiss()
        }
    }

}