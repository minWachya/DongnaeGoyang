package com.example.dongnaegoyang.cat_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dongnaegoyang.R
import com.example.dongnaegoyang.databinding.FragmentCatDetailInfoBinding

private const val TAG = "mmmCatDetailInfoFragment"
private var _binding: FragmentCatDetailInfoBinding? = null
private val binding get() = _binding!!

// 고양이 상세 페이지: '정보' 탭
class CatDetailInfoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?)
    : View {
        _binding = FragmentCatDetailInfoBinding.inflate(inflater, container, false)
        val view = binding.root

        // 사진
        // 사진 가져오기
        val photoAdapter = CatDetailPhotoAdapter()
        binding.rcPhoto.adapter = photoAdapter
        photoAdapter.urls.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTmA-UkwF0vIsZpvg5XYX3U7XsdOhDPz4uveQ&usqp=CAU")
        photoAdapter.urls.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTmA-UkwF0vIsZpvg5XYX3U7XsdOhDPz4uveQ&usqp=CAU")
        photoAdapter.urls.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTmA-UkwF0vIsZpvg5XYX3U7XsdOhDPz4uveQ&usqp=CAU")
        photoAdapter.urls.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTmA-UkwF0vIsZpvg5XYX3U7XsdOhDPz4uveQ&usqp=CAU")
        photoAdapter.urls.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTmA-UkwF0vIsZpvg5XYX3U7XsdOhDPz4uveQ&usqp=CAU")
        photoAdapter.notifyDataSetChanged()
        // 사진 갯수
        binding.tvPhotoCount.text = photoAdapter.itemCount.toString()

        // 건강 정보
        binding.tvHealthCount.text = "2"
        binding.tvHealthTNR.text = "접종 완료"
        binding.tbHealthFood.text = "없음"

        // 최신 업데이트일
        binding.tvUpdateDate.text = "2021-02-24"

        // 등록자
        binding.tvRegistrationUser.text = "간택받은 집사"

        // 나도 관심 좀...: 다른 고양이들 출력
        val anotherCatAdapter = CatDetailInfoAnotherCatAdapter()
        binding.rcAnotherCat.adapter = anotherCatAdapter
        anotherCatAdapter.cats.add(CatDetail(1, 1, 1, 0, 0))
        anotherCatAdapter.cats.add(CatDetail(2, 1, 1, 0, 0))
        anotherCatAdapter.cats.add(CatDetail(0, 1, 0, 0, 1))
        anotherCatAdapter.cats.add(CatDetail(3, 1, 0, 1, 1))
        anotherCatAdapter.cats.add(CatDetail(4, 1, 1, 1, 0))
        anotherCatAdapter.notifyDataSetChanged()

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}