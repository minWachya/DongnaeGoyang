package com.example.dongnaegoyang.ui.cat_detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.dongnaegoyang.R
import com.example.dongnaegoyang.common.KEY_CAT_IDX
import com.example.dongnaegoyang.ui.cat_add.CatAddActivity
import com.example.dongnaegoyang.databinding.ActivityCatDetailBinding
import com.example.dongnaegoyang.login.kakaoLogin.SharedPreferenceController
import com.example.dongnaegoyang.ui.base.BaseActivity
import com.example.dongnaegoyang.ui.cat_detail.info.CatDetailInfoFragment
import com.example.dongnaegoyang.ui.cat_detail.post.CatDetailPostFragment
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "mmmCatDetailActivity"

// 고양이 상세 페이지
@AndroidEntryPoint
class CatDetailActivity : BaseActivity<ActivityCatDetailBinding>(R.layout.activity_cat_detail) {
    private val viewModel: CatDetailViewModel by viewModels()

    // 정보, 오늘 기록 탭 text
    private val tabElement = arrayListOf("정보", "오늘 기록")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setToolbar()    // 툴바 달기
        binding.tabTabLayout.bringToFront() // tabTabLayout을 앞으로 보내서 view1 뒤로 보내기

        val catIdx = intent.getLongExtra(KEY_CAT_IDX, 0)
        getCatDetail(catIdx)  // 고양이 상세 정보 가져오기
        setTapLayout(catIdx)    // 탭 어댑터 생성

        setObserverCatDetail()
        setEditBtnClickListener()

    }

    private fun setToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)   // 뒤로가기
        supportActionBar?.setDisplayShowTitleEnabled(false) // 타이틀 없애기
    }

    private fun getCatDetail(catIdx: Long) {
        val token = SharedPreferenceController.getToken(applicationContext)
        viewModel.getCatDetail(token, catIdx)
    }

    private fun setTapLayout(catIdx: Long) {
        val tabAdapter = CatDetailTabAdapter(this@CatDetailActivity)
        // 프레그먼트, 탭 타이틀 넣기
        tabAdapter.addFragment(CatDetailInfoFragment(catIdx))        // 정보
        tabAdapter.addFragment(CatDetailPostFragment(catIdx))        // 오늘 기록
        binding.tabViewPager.adapter = tabAdapter
        // 탭레이아웃에 뷰 페이저 달기
        TabLayoutMediator(binding.tabTabLayout, binding.tabViewPager) { tab, position ->
            tab.text = tabElement[position]
        }.attach()
        binding.tabViewPager.isUserInputEnabled = false // auto paging off
    }

    private fun setObserverCatDetail() {
        viewModel.catDetailResponse.observe(this) {
            binding.catDetail = it.data
            binding.executePendingBindings()
        }
    }

    private fun setEditBtnClickListener() {
        binding.imgEdit.setOnClickListener {
            // 고양이 수정하기 페이지로 이동
            val intent = Intent(application, CatAddActivity::class.java)
            startActivity(intent)
        }
    }

    // 텝 어댑터
    inner class CatDetailTabAdapter(activity: AppCompatActivity) :  FragmentStateAdapter(activity)  {
        // 프레그먼트 배열
        private val fragmentList = ArrayList<Fragment>()
        // 프레그먼트, 탭 타이틀 추가
        fun addFragment(fragment: Fragment) = fragmentList.add(fragment)
        // 총 프레그먼트 갯수 반환
        override fun getItemCount(): Int = fragmentList.size
        // position 번째 프레그먼트 반환
        override fun createFragment(position: Int): Fragment = fragmentList[position]
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