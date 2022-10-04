package com.example.dongnaegoyang.ui.search_address_add

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import com.example.dongnaegoyang.R
import com.example.dongnaegoyang.databinding.ActivitySearchAddressAddBinding
import com.example.dongnaegoyang.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint


private const val TAG = "mmmAddressSearchActivityAdd"

@AndroidEntryPoint
class SearchAddressActivity :
    BaseActivity<ActivitySearchAddressAddBinding>(R.layout.activity_search_address_add),
    SelectAddressInterface{
    private val viewModel: SearchAddressViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setToolbar()
        searchAddress()
        setObserveAddress()
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

    private fun setObserveAddress() {
        viewModel.addressResponse.observe(this) { response ->
            binding.rvAddress.adapter =
                AddressAdapter(this).apply {
                    submitList(response.documents)
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

    override fun onSelectedAddress(address1: String, address2: String, address3: String) {
        val intent = Intent()
        intent.putExtra("address1", address1)
        intent.putExtra("address2", address2)
        intent.putExtra("address3", address3)
        setResult(RESULT_OK, intent)
        finish()
    }
}