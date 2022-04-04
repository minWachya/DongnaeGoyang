package com.example.dongnaegoyang.address_search

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dongnaegoyang.databinding.ActivitySearchAddressBinding
import com.example.dongnaegoyang.home.MainActivity
import com.example.dongnaegoyang.login.LoginActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val TAG = "mmAddressSearchActivity"

class SearchAddressActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchAddressBinding
    private val listItems = arrayListOf<AddressList>()   // 리사이클러 뷰 아이템
    private val adapter = AddressAdapter(listItems, this@SearchAddressActivity)    // 리사이클러 뷰 어댑터
    private var keyword = "" // 검색 키워드

    companion object {
        const val BASE_URL = "https://dapi.kakao.com/"
        const val API_KEY = "KakaoAK f6eaecbdb5d9cf01733801873c619e45"  // REST API 키
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchAddressBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // 툴바 달기
        setSupportActionBar(binding.toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // 리사이클러 뷰
        binding.recyclerLocation.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerLocation.adapter = adapter

        // 리스트 아이템 클릭 시 해당 주소 메인 액티비티에 전달
        adapter.setItemClickListener(object: AddressAdapter.OnItemClickListener {
            override fun onClick(v: View, position: Int) {
                //없어도 작동되는 코드
                /*var intent = Intent(this@SearchAddressActivity, MainActivity::class.java)
                startActivity(intent)*/
                //finish()

                //작동 안함..
                /*val intent = Intent()
                intent.putExtra("si", "사랑시")
                intent.putExtra("gu", "고백구")
                intent.putExtra("dong", "행복동~~")
                setResult(Activity.RESULT_OK, intent)*/
                finish()
            }
        })

        // 검색 버튼
        binding.btnSearch.setOnClickListener {
            keyword = binding.etAddress.text.toString()
            Log.d("Test : ", keyword)
            if(keyword.isEmpty()){
                Toast.makeText(this, "주소를 입력해 주세요.", Toast.LENGTH_SHORT).show()
            }else {
                searchKeyword(keyword)
            }
        }
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
                Log.d("Test", "(on Response 성공) Raw: ${response.raw()}")
                Log.d("Test", "(on Response 성공) Body: ${response.body()}")
                addItems(response.body())
            }

            override fun onFailure(call: Call<ResultSearchKeyword>, t: Throwable) {
                // 통신 실패
                Log.d(TAG, "통신 실패: ${t.message}")
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
                val item = AddressList(document.address_name,
                    document.address.region_1depth_name,
                    document.address.region_2depth_name,
                    document.address.region_3depth_name)
                listItems.add(item)
            }
            adapter.notifyDataSetChanged()
        } else {
            // 검색 결과 없음
            Toast.makeText(this, "검색 결과가 없습니다", Toast.LENGTH_SHORT).show()
        }
    }

    // 툴바에서 뒤로가기 버튼 클릭 시
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> {  // 뒤로가기 이모지(<-) 클릭
                Toast.makeText(applicationContext, "뒤로가기 클릭", Toast.LENGTH_SHORT).show()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}