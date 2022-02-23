package com.example.dongnaegoyang

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.example.dongnaegoyang.databinding.ActivityCatAddBinding

private const val TAG = "mmmCatAddActivity"
private lateinit var binding: ActivityCatAddBinding

// 고양이 추가 액티비티
class CatAddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCatAddBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // 툴바 달기
        setSupportActionBar(binding.toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // FrameLayout 설정
        val ft = supportFragmentManager.beginTransaction() // 프래그먼트 관리하는 매니저
        ft.replace(R.id.catAddFrameLayout, CatAddFragment1()).commit()
    }

    // 툴바에서 뒤로가기 버튼 클릭 시
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> {  // 뒤로가기 이모지(<-) 클릭
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}