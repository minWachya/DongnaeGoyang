package com.example.dongnaegoyang.ui.cat_add

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.dongnaegoyang.R
import com.example.dongnaegoyang.common.KEY_CAT_ADD_TYPE
import com.example.dongnaegoyang.common.KEY_CAT_IDX
import com.example.dongnaegoyang.common.VALUE_TYPE_CREATE
import com.example.dongnaegoyang.custom.CustomDialog
import com.example.dongnaegoyang.custom.CustomSpinnerTextView
import com.example.dongnaegoyang.data.remote.model.request.CatAddRequest
import com.example.dongnaegoyang.data.remote.model.response.PhotoList
import com.example.dongnaegoyang.databinding.FragmentCatAdd3Binding
import com.example.dongnaegoyang.login.kakaoLogin.SharedPreferenceController
import com.example.dongnaegoyang.ui.base.BaseFragment
import com.example.dongnaegoyang.ui.cat_detail.CatDetailActivity
import com.example.dongnaegoyang.ui.common.MultiUploaderS3Client
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.sangcomz.fishbun.FishBun
import com.sangcomz.fishbun.FishBun.Companion.INTENT_PATH
import com.sangcomz.fishbun.MimeType
import com.sangcomz.fishbun.adapter.image.impl.GlideAdapter
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.schedulers.Schedulers
import java.io.File

private const val TAG = "mmmCatAddFragment3"

// 고양이 추가: 3단계 프레그먼드
@AndroidEntryPoint
class CatAddFragment3 : BaseFragment<FragmentCatAdd3Binding>(R.layout.fragment_cat_add3) {
    private val viewModel: CatAddViewModel by viewModels()

    // BottomDialog 위한 spinner_custom_layout.xml 아이디
    private val arrTextViewId =
        listOf(R.id.title, R.id.text1, R.id.text2, R.id.text3, R.id.text4, R.id.text5, R.id.text6)

    // TNR, 선호 사료 배열
    private lateinit var tnrArray: Array<String>
    private lateinit var foodArray: Array<String>

    private lateinit var photoAdapter: CatAddPhotoAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner

        setToolbar()
        // TNR 선택 스피너 설정
        setTNRSpinnerListener()
        // 선호 사료 선택 스피너 설정
        setFoodSpinnerListener()
        // 사진 어댑터 설정
        setPhotoAdapter()
        // 이전 입력 정보 보여주기
        setPrevInfo(arguments)
        // 버튼 클릭 리스너
        setBtnClickListeners()
        // 2. s3 업로드 후 고양이 정보 저장하기
        setObserverS3Url()
        // 고양이 추가 옵저버
        setObserverCatAddResponse()
    }

    // 툴바 달기
    private fun setToolbar() {
        (activity as CatAddActivity).setSupportActionBar(binding.toolBar)
        (activity as CatAddActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    // TNR 선택 스피너 설정
    private fun setTNRSpinnerListener() {
        val tnrBottomSheetView = layoutInflater.inflate(R.layout.spinner_custom_layout, null)
        val tnrBottomSheetDialog = BottomSheetDialog(requireContext(), R.style.DialogCustomTheme)
        tnrArray = resources.getStringArray(R.array.cat_add3_tnr_array)
        tnrBottomSheetDialog.setContentView(tnrBottomSheetView)
        setBottomSheetView(tnrBottomSheetView, tnrArray, tnrBottomSheetDialog, binding.tnrSpinner)
        binding.tnrSpinner.textView.setOnClickListener { tnrBottomSheetDialog.show() }
    }

    // 선호 사료 선택 스피너 설정
    private fun setFoodSpinnerListener() {
        val foodBottomSheetView = layoutInflater.inflate(R.layout.spinner_custom_layout, null)
        val foodBottomSheetDialog = BottomSheetDialog(requireContext(), R.style.DialogCustomTheme)
        foodArray = resources.getStringArray(R.array.cat_add3_food_array)
        foodBottomSheetDialog.setContentView(foodBottomSheetView)
        setBottomSheetView(
            foodBottomSheetView,
            foodArray,
            foodBottomSheetDialog,
            binding.foodSpinner
        )
        binding.foodSpinner.textView.setOnClickListener { foodBottomSheetDialog.show() }
    }

    // 사진 어댑터 설정
    private fun setPhotoAdapter() {
        photoAdapter = CatAddPhotoAdapter(binding)
        binding.rcPhoto.adapter = photoAdapter
        setSelectPhoto()    // 사진 선택 설정
    }

    // 사진 선택 설정
    private fun setSelectPhoto() {
        // 사진 설정 부분
        val photoResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val data = result.data
                    if (data != null) {
                        // 이전 데이터 삭제
                        photoAdapter.imgUris.clear()
                        // 새 데이터 저장
                        val photoUriArr = data.getParcelableArrayListExtra<Uri>(INTENT_PATH)!!
                        for (uri in photoUriArr) photoAdapter.imgUris.add(uri)
                        photoAdapter.notifyDataSetChanged()
                    }
                    // 사진 갯수
                    binding.tvSelectCount.text = photoAdapter.itemCount.toString()
                }
            }
        binding.layoutSelectPhoto.setOnClickListener {
            FishBun.with(this@CatAddFragment3)
                .setImageAdapter(GlideAdapter())
                .setIsUseDetailView(true)                   // 상세 사진 보기 true
                .setMaxCount(6)                             // 최대 사진 개수
                .setSelectedImages(photoAdapter.imgUris)    // 이전에 선택했던 사진 uri 배열
                .exceptMimeType(listOf(MimeType.GIF))       // GIT 제외
                .setActionBarColor(Color.parseColor("#473A22"), Color.parseColor("#5D4037"), false)
                .setActionBarTitleColor(Color.parseColor("#ffffff"))
                .startAlbumWithActivityResultCallback(photoResultLauncher)
        }
    }

    // 스피너 선택 설정
    private fun setBottomSheetView(
        bottomSheetView: View,
        arr: Array<String>,
        dialog: BottomSheetDialog,
        spinner: CustomSpinnerTextView
    ) {
        for (i in arr.indices) {
            val textView = bottomSheetView.findViewById<TextView>(arrTextViewId[i])
            textView.text = arr[i]
            textView.visibility = View.VISIBLE
            textView.setOnClickListener {
                spinner.textView.text = arr[i]
                dialog.dismiss()
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
        if (bundle?.getString("tnr") != null) {
            val tnr = bundle.getString("tnr")    // TNR
            val food = bundle.getString("food")  // 선호 사료
            binding.tnrSpinner.textView.text = tnr
            binding.foodSpinner.textView.text = food
            // 사진
            val type =
                requireActivity().intent.getStringExtra(KEY_CAT_ADD_TYPE) ?: VALUE_TYPE_CREATE
            if (type == VALUE_TYPE_CREATE) {
                val photoUriArr =
                    bundle.getParcelableArrayList<Uri>("uriArr")//data.getParcelableArrayListExtra<Uri>(INTENT_PATH)!!
                if (photoUriArr != null) {
                    for (uri in photoUriArr) photoAdapter.imgUris.add(uri as Uri)
                    photoAdapter.notifyDataSetChanged()
                    binding.tvSelectCount.text = photoAdapter.imgUris.size.toString()
                }
            } else {
                val photoList = bundle.get("photoList")
            }
        }
    }

    private fun setBtnClickListeners() {
        // <이전> 버튼 클릭: 2단계로 이동
        binding.btnBack.setOnClickListener { setFrag(CatAddFragment2(), arguments) }

        // <등록> 버튼 클릭: 1. 고양이 사진 저장 2. 고양이 정보 저장
        binding.btnOK3.setOnClickListener {
            // dialog 보이기
            val gugun = arguments?.getString("sido", "null")
            val dong = arguments?.getString("dong", "null")
            CustomDialog(
                "등록 확인", "$gugun $dong 에 새로운 고영희를 등록하시겠습니까?", null
            ) {
                // 1. OK 버튼 클릭 시 고양이 사진 S3에 저장
                val multiUploadHashMap = linkedMapOf<String, File>()
                for (i in 0 until photoAdapter.imgUris.size) {
                    val path = getRealPathFromURI(photoAdapter.imgUris[i]).toString()
                    val file = File(path)
                    multiUploadHashMap[file.name] = file
                }
                if (multiUploadHashMap.size != 0) uploadImageToS3(multiUploadHashMap)
                else postCatAdd(arguments!!)
            }.show(parentFragmentManager, "CustomDialog")
        }
    }


    private fun getRealPathFromURI(contentUri: Uri): String? {
        val cursor = requireContext().contentResolver.query(contentUri, null, null, null, null)
        cursor?.moveToNext()
        val path = cursor?.getString(cursor.getColumnIndex("_data"))
        cursor?.close()
        return path
    }

    private fun uploadImageToS3(map: LinkedHashMap<String, File>) {
        MultiUploaderS3Client(
            "cat-img/add",
            requireContext(),
            viewModel
        ).uploadMultiple(map)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(Schedulers.io())
            ?.subscribe()
    }

    // 2. s3 업로드 후 고양이 정보 저장하기
    private fun setObserverS3Url() {
        viewModel.arrS3Url.observe(viewLifecycleOwner) {
            postCatAdd(arguments!!)
        }
    }

    private fun postCatAdd(bundle: Bundle) {
        val token = SharedPreferenceController.getToken(requireContext())
//        if (photoAdapter.imgUris.size == 0) viewModel.arrS3Url.value!!
        val tnr: String? = if (binding.tnrSpinner.textView.text.toString() != "")
            when (binding.tnrSpinner.textView.text.toString()) {
                "O" -> "접종 완료"
                "X" -> "접종 미완료"
                else -> "모름"
            } else null
        val food: String? = if (binding.foodSpinner.textView.text.toString() != "")
            binding.foodSpinner.textView.text.toString()
        else null
        val body = CatAddRequest(
            name = bundle.getString("name", "null"),
            color = bundle.getInt("fur"),
            size = bundle.getInt("size"),
            ear = bundle.getInt("ear"),
            tail = bundle.getInt("tail"),
            whisker = bundle.getInt("whiskers"),
            oftenSeen = bundle.getString("place", "null"),
            sex = bundle.getString("gender", "null"),
            age = bundle.getString("age", "null"),
            note = bundle.getString("note", "null"),
            sido = bundle.getString("sido", "null"),
            gugun = bundle.getString("gugun", "null"),
            tnr = tnr,
            feed = food,
            photoList = if (photoAdapter.imgUris.size != 0) listOf(*(viewModel.arrS3Url.value!!)) else null
        )
        viewModel.postCatAdd(token, body)
    }

    private fun setObserverCatAddResponse() {
        viewModel.catAddResponse.observe(viewLifecycleOwner) {
            Toast.makeText(context, "고양이를 성공적으로 추가하였습니다.", Toast.LENGTH_SHORT).show()
            Intent(context, CatDetailActivity::class.java).apply {
                putExtra(KEY_CAT_IDX, it.data)
                startActivity(this)
            }
            requireActivity().finish()
        }
    }


    // 프레그먼트 이동
    private fun setFrag(catAddFragment: Fragment, bundle: Bundle?) {
        // 3단계 정보 함께 보내기
        if (bundle != null) {
            val tnr = binding.tnrSpinner.textView.text.toString()     // TNR
            val food = binding.foodSpinner.textView.text.toString() // 선호 사료
            bundle.putString("tnr", tnr)
            bundle.putString("food", food)
            bundle.putParcelableArrayList("uriArr", photoAdapter.imgUris)
            catAddFragment.arguments = bundle
        }
        // 프레그먼트에 정보 전달 + 이동
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.catAddFrameLayout, catAddFragment).commit()
    }

}