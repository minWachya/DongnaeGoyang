package com.example.dongnaegoyang.ui.cat_add

import android.os.Bundle
import android.view.MenuItem
import com.example.dongnaegoyang.R
import com.example.dongnaegoyang.databinding.ActivityCatAddBinding
import com.example.dongnaegoyang.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "mmmCatAddActivity"

// 고양이 추가 액티비티
@AndroidEntryPoint
class CatAddActivity :  BaseActivity<ActivityCatAddBinding>(R.layout.activity_cat_add) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // FrameLayout 설정
        setFrameLayout()
    }

    private fun setFrameLayout() {
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