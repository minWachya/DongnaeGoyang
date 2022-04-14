package com.example.dongnaegoyang.cat_add

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.example.dongnaegoyang.R
import com.example.dongnaegoyang.custom.CustomSpinnerTextView
import com.example.dongnaegoyang.databinding.FragmentCatAdd2Binding
import com.google.android.material.bottomsheet.BottomSheetDialog

private const val TAG = "mmmCatAddFragment2"

private var _binding: FragmentCatAdd2Binding? = null
private val binding get() = _binding!!

// 고양이 추가: 2단계 프레그먼드
class CatAddFragment2 : Fragment() {
    // 이름, 주 출몰지, 특이사항 입력 여부
    private var checkName = false
    private var checkPlace = false
    private var checkNote = false

    // 성별, 나이 배열
    private lateinit var genderArray: Array<String>
    private lateinit var ageArray: Array<String>

    // BottomDialog 위한 spinner_custom_layout.xml 아이디
    private val arrTextViewId = listOf(R.id.title, R.id.text1, R.id.text2, R.id.text3, R.id.text4, R.id.text5, R.id.text6)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCatAdd2Binding.inflate(inflater, container, false)
        val view = binding.root

        // 툴바 달기
        (activity as CatAddActivity).setSupportActionBar(binding.toolBar)
        (activity as CatAddActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

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
        genderArray = resources.getStringArray(R.array.cat_add2_gender_array)
        genderBottomSheetDialog.setContentView(genderBottomSheetView)
        setBottomSheetView(genderBottomSheetView, genderArray, genderBottomSheetDialog, binding.genderSpinner)
        binding.genderSpinner.textView.setOnClickListener { genderBottomSheetDialog.show() }

        // 추정 나이 선택 스피너 설정
        val ageBottomSheetView = layoutInflater.inflate(R.layout.spinner_custom_layout, null)
        val ageBottomSheetDialog = BottomSheetDialog(requireContext(), R.style.DialogCustomTheme)
        ageArray = resources.getStringArray(R.array.cat_add2_age_array)
        ageBottomSheetDialog.setContentView(ageBottomSheetView)
        setBottomSheetView(ageBottomSheetView, ageArray, ageBottomSheetDialog, binding.ageSpinner)
        binding.ageSpinner.textView.setOnClickListener { ageBottomSheetDialog.show() }

        val bundle1 = arguments
        // 이전 선택 정보 보여주기
        setPrevInfo(bundle1)

        // <이전> 버튼 클릭: 1단계로 이동(2단계 정보도 함께 전달)
        binding.btnBack.setOnClickListener {
            setFrag(CatAddFragment1(), bundle1)
        }

        // <다음> 버튼 클릭: 3단계로 이동
        binding.btnOK2.setOnClickListener {
            setFrag(CatAddFragment3(), bundle1)
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
                && binding.genderSpinner.textView.text.isNotEmpty() && binding.ageSpinner.textView.text.isNotEmpty()
    }

    // 스피너 선택 설정
    private fun setBottomSheetView(bottomSheetView: View, arr: Array<String>, dialog: BottomSheetDialog, spinner: CustomSpinnerTextView) {
        for (i in arr.indices) {
            val textView = bottomSheetView.findViewById<TextView>(arrTextViewId[i])
            textView.text = arr[i]
            textView.visibility = View.VISIBLE
            textView.setOnClickListener {
                spinner.textView.text = arr[i]
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

    // 이전 정보 보여주기
    private fun setPrevInfo(bundle: Bundle?) {
        if (bundle?.getString("name") != null) {
            val name = bundle.getString("name")    // 이름
            val place = bundle.getString("place")  // 주 출몰지
            val gender = bundle.getInt("gender", -1)   // 설별
            val age = bundle.getInt("age", -1)         // 추정 나이
            val note = bundle.getString("note")    // 특이사항
            binding.editName.setText(name)
            binding.editPlace.setText(place)
            if(gender != -1) binding.genderSpinner.textView.text = genderArray[gender]
            if(age != -1) binding.ageSpinner.textView.text = ageArray[age]
            binding.editSpecialNote.setText(note)
        }
    }

    // 프레그먼트 이동
    private fun setFrag(catAddFragment: Fragment, bundle: Bundle?) {
        // 2단계 정보 함께 보내기
        if (bundle != null) {
            val name = binding.editName.text.toString()                 // 이름
            val place = binding.editPlace.text.toString()               // 주 출몰지
            val gender = genderArray.indexOf(binding.genderSpinner.textView.text)// 성별
            val age = ageArray.indexOf(binding.ageSpinner.textView.text)         // 추정 나이
            val note = binding.editSpecialNote.text.toString()          // 특이사항
            bundle.putString("name", name)
            bundle.putString("place", place)
            bundle.putInt("gender", gender)
            bundle.putInt("age", age)
            bundle.putString("note", note)
            catAddFragment.arguments = bundle
        }
        // 프레그먼트에 정보 전달 + 이동
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.catAddFrameLayout, catAddFragment).commit()
    }

}