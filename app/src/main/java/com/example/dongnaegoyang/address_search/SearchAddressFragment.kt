package com.example.dongnaegoyang.address_search

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dongnaegoyang.databinding.FragmentSearchAddressBinding
import com.example.dongnaegoyang.home.MainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val TAG = "mmmCatSearchFragment"

class SearchAddressFragment : Fragment() {
    private var _binding: FragmentSearchAddressBinding? = null
    private val binding get() = _binding!!

    private val listItems = arrayListOf<AddressList>()   // 리사이클러 뷰 아이템
    private val adapter = AddressAdapter(listItems)    // 리사이클러 뷰 어댑터
    private var keyword = "" // 검색 키워드

    var activity: SearchAddressActivity? = null

    companion object {
        const val BASE_URL = "https://dapi.kakao.com/"
        const val API_KEY = "KakaoAK f6eaecbdb5d9cf01733801873c619e45"  // REST API 키
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchAddressBinding.inflate(inflater, container, false)
        val view = binding.root

        // 리사이클러 뷰
        binding.recyclerLocation.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerLocation.adapter = adapter

        // 리스트 아이템 클릭 시 해당 주소 메인 액티비티에 전달
        adapter.setItemClickListener(object: AddressAdapter.OnItemClickListener {
            override fun onClick(v: View, position: Int) {
                var intent = Intent(context, MainActivity::class.java)
                // MainActivity에 주소(동) 보내기
                //intent.putExtra("si", si.text.toString())
                startActivity(intent)
                activity?.finish()
            }
        })
        // 검색 버튼
        binding.btnSearch.setOnClickListener {
            keyword = binding.etAddress.text.toString()
            Log.d("Test", keyword)
            if(keyword.isEmpty()){
                Toast.makeText(context, "주소를 입력해 주세요.", Toast.LENGTH_SHORT).show()
            }else {
                searchKeyword(keyword)
            }
        }

        return view
    }

    // 키워드 검색 함수
    private fun searchKeyword(keyword: String) {
        val retrofit = Retrofit.Builder()          // Retrofit 구성
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val api = retrofit.create(KakaoAPI::class.java)            // 통신 인터페이스를 객체로 생성
        val call = api.getSearchKeyword(API_KEY, keyword)    // 검색 조건 입력

        // API 서버에 요청
        call.enqueue(object: Callback<ResultSearchKeyword> {
            override fun onResponse(
                call: Call<ResultSearchKeyword>,
                response: Response<ResultSearchKeyword>
            ) {
                // 통신 성공 (검색 결과는 response.body()에 담겨있음)
                Log.d("Test", "Raw: ${response.raw()}")
                Log.d("Test", "Body: ${response.body()}")
                addItems(response.body())
            }

            override fun onFailure(call: Call<ResultSearchKeyword>, t: Throwable) {
                // 통신 실패
                Log.w(TAG, "통신 실패: ${t.message}")
            }
        })
    }

    // 검색 결과 처리 함수
    private fun addItems(searchResult: ResultSearchKeyword?) {
        if (!searchResult?.documents.isNullOrEmpty()) {
            // 검색 결과 있음
            listItems.clear()    // 리스트 초기화
            for (document in searchResult!!.documents) {
                // 결과를 리사이클러 뷰에 추가
                val item = AddressList(document.region_1depth_name,
                    document.region_2depth_name,
                    document.region_3depth_name)
                listItems.add(item)
            }
            adapter.notifyDataSetChanged()
        } else {
            // 검색 결과 없음
            Toast.makeText(context, "검색 결과가 없습니다", Toast.LENGTH_SHORT).show()
        }
    }
}