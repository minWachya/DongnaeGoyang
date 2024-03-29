package com.example.dongnaegoyang.cat_search

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.dongnaegoyang.R
import com.example.dongnaegoyang.databinding.ActivitySearchCatBinding
import com.example.dongnaegoyang.databinding.FragmentSearchCatBinding

private const val TAG = "mmmCatSearchFragment"

private var _binding: FragmentSearchCatBinding? = null
private val binding get() = _binding!!

class SearchCatActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchCatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchCatBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // 툴바 달기
        setSupportActionBar(binding.toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // FrameLayout 설정
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.searchCatFrame, SearchCatFragment()).commit()
    }

    // 툴바에서 버튼 클릭 시
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}