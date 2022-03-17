package com.example.dongnaegoyang.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dongnaegoyang.R
import com.example.dongnaegoyang.databinding.ActivitySignUpDialogBinding

class SignUpDialogActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpDialogBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


    }
}