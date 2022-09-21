package com.example.dongnaegoyang.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dongnaegoyang.home.MainActivity
import com.example.dongnaegoyang.ui.onboarding.OnBoardingActivity

// 스플래시 화면
@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 온보딩 화면 보이기
        val sharedPreference = getSharedPreferences("onBoarding", MODE_PRIVATE)
        val isShow = sharedPreference.getBoolean("isShow", true)
        if(isShow) {
            val intent = Intent(this@SplashActivity, OnBoardingActivity::class.java)
            startActivity(intent)
            finish()
        }
        else {
            // 로그인 이동...해야하는데 아직 미완인 듯해서 메인 화면으로 이동
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}