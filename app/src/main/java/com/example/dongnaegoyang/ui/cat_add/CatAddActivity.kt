package com.example.dongnaegoyang.ui.cat_add

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.core.os.bundleOf
import com.example.dongnaegoyang.R
import com.example.dongnaegoyang.common.KEY_CAT_ADD_TYPE
import com.example.dongnaegoyang.common.KEY_CAT_IDX
import com.example.dongnaegoyang.common.VALUE_TYPE_CREATE
import com.example.dongnaegoyang.common.VALUE_TYPE_MODIFY
import com.example.dongnaegoyang.data.remote.model.response.PhotoList
import com.example.dongnaegoyang.databinding.ActivityCatAddBinding
import com.example.dongnaegoyang.login.kakaoLogin.SharedPreferenceController
import com.example.dongnaegoyang.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "mmmCatAddActivity"

// 고양이 추가 액티비티
@AndroidEntryPoint
class CatAddActivity :  BaseActivity<ActivityCatAddBinding>(R.layout.activity_cat_add) {
    private val viewModel: CatAddViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 추가 모드인지, 수정 모드인지 구분
        val type = getType()
        setFrameLayout(type)
        setObserverCatModifyData()
    }

    private fun getType(): String = intent.getStringExtra(KEY_CAT_ADD_TYPE) ?: VALUE_TYPE_CREATE

    private fun setFrameLayout(type: String) {
        if(type == VALUE_TYPE_MODIFY) getCatModifyData()
        else {
            // FrameLayout 설정
            val ft = supportFragmentManager.beginTransaction() // 프래그먼트 관리하는 매니저
            ft.replace(R.id.catAddFrameLayout, CatAddFragment1()).commit()
        }
    }

    private fun getCatModifyData() {
        val token = SharedPreferenceController.getToken(applicationContext)
        val catIdx = intent.getLongExtra(KEY_CAT_IDX, 6L)
        viewModel.getCatModifyData(token, catIdx)
    }

    private fun setObserverCatModifyData() {
        viewModel.catModifyDataResponse.observe(this) {
            val bundle = bundleOf(
                "size" to it.data.appearance.size,
                "fur" to it.data.appearance.color,
                "ear" to it.data.appearance.ear,
                "tail" to it.data.appearance.tail,
                "whiskers" to it.data.appearance.whisker,
                "name" to it.data.name,
                "place" to it.data.oftenSeen,
                "gender" to it.data.sex,
                "age" to it.data.age,
                "note" to it.data.note,
                "sido" to it.data.sido,
                "gugun" to it.data.gugun,
                "tnr" to it.data.tnr,
                "food" to it.data.feed,
                "urlArr" to it.data.photoList
            )
            // FrameLayout 설정
            val ft = supportFragmentManager.beginTransaction() // 프래그먼트 관리하는 매니저
            ft.replace(R.id.catAddFrameLayout, CatAddFragment1().apply { arguments = bundle }).commit()
        }
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