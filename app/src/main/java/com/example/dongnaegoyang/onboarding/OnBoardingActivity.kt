package com.example.dongnaegoyang.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.dongnaegoyang.databinding.ActivityOnBoardingBinding
import com.example.dongnaegoyang.home.MainActivity

private const val TAG = "mmmCatAddActivity"
private lateinit var binding: ActivityOnBoardingBinding

// 온보딩 화면
class OnBoardingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val pager = binding.pager
        val indicator = binding.pageIndicatorView

        // 어댑터 연결
        pager.adapter = VpagerAdapter(this@OnBoardingActivity)
        pager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        pager.offscreenPageLimit = 4
        // indicator 설정
        indicator.count = 3         // 전체 3
        indicator.selection = 0     // 현재 0
        // indicator 현재 포지션으로 바뀌는 애니메이션 실핼
        pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                indicator.selection = position
            }
        })

        // <시작하기>버튼 누르면?
        binding.btnStart.setOnClickListener {
            // 다음에는 안 보이게 설정
            val sharedPreference = getSharedPreferences("onboarding", MODE_PRIVATE)
            val editor = sharedPreference.edit()
            editor.putBoolean("isShow", false)
            editor.apply()

            // 메인 화면으로 이동
            val intent = Intent(this@OnBoardingActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    inner class VpagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
        // 어댑터에서 다루는 아이템의 갯수
        override fun getItemCount() = 3 // 도움말 이미지 3장(예시

        // 현재 선택된 프레그먼트 번호에 따른 프레그먼트 보여주기
        override fun createFragment(position: Int): Fragment {
            return when(position) {
                0 -> OnBoardingFragment1()
                1 -> OnBoardingFragment2()
                2 -> OnBoardingFragment1()
                // 디폴트는 시작 페이지로
                else -> OnBoardingFragment1()
            }
        }
    }

}