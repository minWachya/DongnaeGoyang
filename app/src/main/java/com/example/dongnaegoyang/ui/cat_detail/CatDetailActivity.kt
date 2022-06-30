package com.example.dongnaegoyang.ui.cat_detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.dongnaegoyang.ui.cat_add.CatAddActivity
import com.example.dongnaegoyang.databinding.ActivityCatDetailBinding
import com.example.dongnaegoyang.ui.cat_detail_info.CatDetailInfoFragment
import com.example.dongnaegoyang.ui.common.ViewModelFactory
import com.google.android.material.tabs.TabLayoutMediator

private const val TAG = "mmmCatDetailActivity"

// 고양이 상세 페이지
class CatDetailActivity : AppCompatActivity() {
    private val viewModel: CatDetailViewModel by viewModels { ViewModelFactory(applicationContext) }
    private lateinit var binding: ActivityCatDetailBinding

    // 정보, 오늘 기록 탭 text
    private val tabElement = arrayListOf("정보", "오늘 기록")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCatDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setToolbar()    // 툴바 달기
        setCatDetail()  // 고양이 상세 정보 가져오기

        binding.tabTabLayout.bringToFront() // tabTabLayout을 앞으로 보내서 view1 뒤로 보내기

        // 수정 버튼 클릭
        if (true) binding.imgEdit.visibility = View.VISIBLE
        binding.imgEdit.setOnClickListener {
            // 고양이 수정하기 페이지로 이동
            val intent = Intent(application, CatAddActivity::class.java)
            startActivity(intent)
        }

        // 탭 어댑터 생성
        val tabAdapter = CatDetailTabAdapter(this@CatDetailActivity)
        // 프레그먼트, 탭 타이틀 넣기
        tabAdapter.addFragment(CatDetailInfoFragment())        // 정보
        tabAdapter.addFragment(CatDetailPostFragment())        // 오늘 기록
        binding.tabViewPager.adapter = tabAdapter
        // 탭레이아웃에 뷰 페이저 달기
        TabLayoutMediator(binding.tabTabLayout, binding.tabViewPager) { tab, position ->
            tab.text = tabElement[position]
        }.attach()
        binding.tabViewPager.isUserInputEnabled = false // auto paging off

    }

    private fun setCatDetail() {
        binding.catDetail = viewModel.catDetail.value
        binding.executePendingBindings()
    }

    private fun setToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)   // 뒤로가기
        supportActionBar?.setDisplayShowTitleEnabled(false) // 타이틀 없애기
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