package com.example.dongnaegoyang.ui.search_address_add

import com.example.dongnaegoyang.address_search.AddressAdapter
import com.example.dongnaegoyang.address_search.AddressList
import com.example.dongnaegoyang.address_search.KakaoAPI
import com.example.dongnaegoyang.address_search.ResultSearchKeyword
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dongnaegoyang.R
import com.example.dongnaegoyang.databinding.ActivitySearchAddressBinding
import com.example.dongnaegoyang.ui.base.BaseActivity
import com.example.dongnaegoyang.ui.cat_add.CatAddActivity
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val TAG = "mmmAddressSearchActivity"

@AndroidEntryPoint
class SearchAddressActivity : BaseActivity<ActivitySearchAddressBinding>(R.layout.activity_search_address) {
    private val viewModel: SearchAddressViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setToolbar()
        searchAddress()
        setObserveAddress()
    }

    private fun setObserveAddress() {
        viewModel.addressResponse.observe(this) { response ->
            Log.d("mmm", response.toString())
        }

    }

    // 툴바 달기
    private fun setToolbar() {
        setSupportActionBar(binding.toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun searchAddress() {
        binding.btnSearch.setOnClickListener {
            val keyword = binding.etAddress.text.toString()
            if(keyword.isEmpty()){
                Toast.makeText(this, "주소를 입력해 주세요.", Toast.LENGTH_SHORT).show()
            }else {
                viewModel.getAddressListResponse(keyword, 1, 10, "KakaoAK f6eaecbdb5d9cf01733801873c619e45")
            }
        }
    }

    // 툴바에서 뒤로가기 버튼 클릭 시
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}