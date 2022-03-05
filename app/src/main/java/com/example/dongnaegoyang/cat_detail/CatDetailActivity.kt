package com.example.dongnaegoyang.cat_detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
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
        supportActionBar?.setDisplayHomeAsUpEnabled(true)   // 뒤로가기
        supportActionBar?.setDisplayShowTitleEnabled(false) // 타이틀 없애기

        binding.tabTabLayout.bringToFront() // tabTabLayout을 앞으로 보내서 view1 뒤로 보내기

        // 고양이 정보 가져오기
        getCatInfo()

        // 수정 버튼 클릭
        binding.imgEdit.setOnClickListener {
            Toast.makeText(applicationContext, "수정하기", Toast.LENGTH_SHORT).show()
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

    // 고양이 정보 가져오기
    private fun getCatInfo() {
        binding.tvName.text = "나비"      // 이름
        binding.tvGender.text = "암컷"    // 성별
        binding.tvAge.text = "2살 추정"   // 추정 나이
        binding.tvPlace.text = "XX구 XX동"// 동네
        binding.tvHotPlace.text = "경의선숲길 부산집 앞에서 주로 출몰" // 주 출몰 장소
        // 상세 정보
        binding.tvNote.text = "부산집 사장님이 가끔 먹이를 챙겨주셔서 그런지 사람을 경계하지 않아요.\n카메라 들이대면 포즈 취해주는 개냥이"
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