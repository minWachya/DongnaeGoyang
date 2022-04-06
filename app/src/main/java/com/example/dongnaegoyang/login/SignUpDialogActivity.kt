package com.example.dongnaegoyang.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.dongnaegoyang.R
import com.example.dongnaegoyang.databinding.ActivitySignUpDialogBinding
import com.example.dongnaegoyang.login.kakaoLogin.KakaoLogin

class SignUpDialogActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpDialogBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

/*        binding.btnKakaoStart.setOnClickListener {
            Log.d("sign up", "카카오로 시작하기 버튼 눌림")
            KakaoLogin(parent).doKakaoLogin()
        }*/

    }
}