package com.example.dongnaegoyang.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dongnaegoyang.R
import com.example.dongnaegoyang.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)



    }
}