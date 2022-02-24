package com.example.dongnaegoyang.cat_detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.dongnaegoyang.R
import com.example.dongnaegoyang.databinding.ActivityCatDetailBinding
import com.google.android.material.tabs.TabLayoutMediator

private const val TAG = "mmmCatDetailActivity"
private lateinit var binding: ActivityCatDetailBinding

// 고양이 상세 페이지
class CatDetailActivity : AppCompatActivity() {
    // 정보, 오늘 기록 탭 text
    private val tabElement = arrayListOf("정보", "오늘 기록")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCatDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // 툴바 달기
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // 어댑터 생성
        val tabAdapter = CatDetailTabAdapter(this@CatDetailActivity)
        // 프레그먼트, 탭 타이틀 넣기(프레그먼트 하나로 통일~)
        tabAdapter.addFragment(CatDetailInfoFragment())        // 정보
        tabAdapter.addFragment(CatDetailNoteFragment())        // 오늘 기록
        binding.tabViewPager.adapter = tabAdapter
        // 탭레이아웃에 뷰 페이저 달기
        TabLayoutMediator(binding.tabTabLayout, binding.tabViewPager) { tab, position ->
            tab.text = tabElement[position]
        }.attach()

    }

    // 텝 어댑터
    class CatDetailTabAdapter(activity: AppCompatActivity) :  FragmentStateAdapter(activity)  {
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