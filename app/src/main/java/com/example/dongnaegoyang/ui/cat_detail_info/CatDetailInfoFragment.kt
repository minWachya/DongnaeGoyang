package com.example.dongnaegoyang.ui.cat_detail_info

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.dongnaegoyang.common.CustomCat
import com.example.dongnaegoyang.databinding.FragmentCatDetailInfoBinding
import com.example.dongnaegoyang.ui.common.ViewModelFactory

private const val TAG = "mmmCatDetailInfoFragment"

// 고양이 상세 페이지: '정보' 탭
class CatDetailInfoFragment : Fragment() {
    private val viewModel: CatDetailInfoViewModel by viewModels { ViewModelFactory(requireContext()) }
    private lateinit var binding: FragmentCatDetailInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?)
    : View {
        binding = FragmentCatDetailInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.catDetailInfo = viewModel.catDetailInfo.value

        // 사진 가져오기
        val photoAdapter = CatDetailPhotoAdapter()
        binding.rcPhoto.adapter = photoAdapter
        photoAdapter.urls.addAll(viewModel.catDetailInfo.value!!.photoList)
        photoAdapter.notifyDataSetChanged()

        // 나도 관심 좀...: 다른 고양이들 출력
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