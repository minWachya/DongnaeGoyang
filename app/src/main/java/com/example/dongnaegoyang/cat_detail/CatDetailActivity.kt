package com.example.dongnaegoyang.cat_detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dongnaegoyang.R
import com.example.dongnaegoyang.databinding.ActivityCatDetailBinding

private const val TAG = "mmmCatDetailActivity"
private lateinit var binding: ActivityCatDetailBinding

// 고양이 상세 페이지
class CatDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCatDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // 툴바 달기
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}