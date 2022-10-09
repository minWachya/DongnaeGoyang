package com.example.dongnaegoyang.ui.cat_detail.info

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.dongnaegoyang.R
import com.example.dongnaegoyang.common.KEY_CAT_IDX
import com.example.dongnaegoyang.data.remote.model.response.CustomCat
import com.example.dongnaegoyang.data.remote.model.response.PhotoList
import com.example.dongnaegoyang.databinding.FragmentCatDetailInfoBinding
import com.example.dongnaegoyang.ui.base.BaseFragment
import com.example.dongnaegoyang.ui.cat_detail.CatDetailActivity
import com.example.dongnaegoyang.ui.common.EventObserver
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "mmmCatDetailInfoFragment"

// 고양이 상세 페이지: '정보' 탭
@AndroidEntryPoint
class CatDetailInfoFragment : BaseFragment<FragmentCatDetailInfoBinding>(R.layout.fragment_cat_detail_info) {
    private val viewModel: CatDetailInfoViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner

        val catIdx = arguments?.getLong(KEY_CAT_IDX) ?: 6L
        getCatDetailInfo(catIdx)
        setObserverCatDetailInfo()
        setObserverClickAnotherCat()
    }

    private fun getCatDetailInfo(catIdx: Long) {
        viewModel.getCatDetailInfo(catIdx)
    }

    private fun setObserverCatDetailInfo() {
        viewModel.catDetailInfoResponse.observe(viewLifecycleOwner) {
            binding.catDetail = it.data
            binding.executePendingBindings()
            setPhotoAdapter(it.data.photoList)
            setAnotherCatAdapter(it.data.otherCatList)
        }
    }

    private fun setPhotoAdapter(photoList: List<PhotoList>) {
        binding.rcPhoto.adapter = CatDetailPhotoAdapter().apply {
            submitList(photoList)
        }
    }

    private fun setAnotherCatAdapter(anotherCatList: List<CustomCat>) {
        binding.rcAnotherCat.adapter = CatDetailInfoAnotherCatAdapter(viewModel).apply {
            submitList(anotherCatList)
            notifyDataSetChanged()
        }
    }

    private fun setObserverClickAnotherCat() {
        viewModel.openCatDetailEvent.observe(viewLifecycleOwner, EventObserver {
            Intent(context, CatDetailActivity::class.java).apply {
                putExtra(KEY_CAT_IDX, it)
                startActivity(this)
            }
        })
    }
}