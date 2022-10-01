package com.example.dongnaegoyang.ui.cat_detail_info

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.dongnaegoyang.R
import com.example.dongnaegoyang.common.CustomCat
import com.example.dongnaegoyang.databinding.FragmentCatDetailInfoBinding
import com.example.dongnaegoyang.ui.base.BaseFragment
import com.example.dongnaegoyang.ui.common.ViewModelFactory

private const val TAG = "mmmCatDetailInfoFragment"

// 고양이 상세 페이지: '정보' 탭
class CatDetailInfoFragment : BaseFragment<FragmentCatDetailInfoBinding>(R.layout.fragment_cat_detail_info) {
    private val viewModel: CatDetailInfoViewModel by viewModels { ViewModelFactory(requireContext()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner

        // 사진 가져오기
        setPhotoAdapter()

        // 나도 관심 좀...: 다른 고양이들 출력
        setAnotherCatAdapter()
    }

    private fun setPhotoAdapter() {
        val photoAdapter = CatDetailPhotoAdapter()
        binding.rcPhoto.adapter = photoAdapter
        photoAdapter.urls.addAll(viewModel.catDetailInfo.value!!.photoList)
        photoAdapter.notifyDataSetChanged()
    }

    private fun setAnotherCatAdapter() {
        val anotherCatAdapter = CatDetailInfoAnotherCatAdapter()
        binding.rcAnotherCat.adapter = anotherCatAdapter
        anotherCatAdapter.cats.add(CustomCat(1, 1, 1, 0, 0))
        anotherCatAdapter.cats.add(CustomCat(2, 1, 1, 0, 0))
        anotherCatAdapter.cats.add(CustomCat(0, 1, 0, 0, 1))
        anotherCatAdapter.cats.add(CustomCat(3, 1, 0, 1, 1))
        anotherCatAdapter.cats.add(CustomCat(4, 1, 1, 1, 0))
        anotherCatAdapter.notifyDataSetChanged()
    }

}