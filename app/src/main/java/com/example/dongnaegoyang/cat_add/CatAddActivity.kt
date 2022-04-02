package com.example.dongnaegoyang.cat_add

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.dongnaegoyang.R
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

        // FrameLayout 설정
        val ft = supportFragmentManager.beginTransaction() // 프래그먼트 관리하는 매니저
        ft.replace(R.id.catAddFrameLayout, CatAddFragment1()).commit()
    }

    // 툴바에서 뒤로가기 버튼 클릭 시
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            // 뒤로가기 이모지(<-) 클릭
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

}