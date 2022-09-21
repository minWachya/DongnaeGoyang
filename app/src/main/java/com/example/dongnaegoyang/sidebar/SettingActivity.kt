package com.example.dongnaegoyang.sidebar

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.dongnaegoyang.R
import com.example.dongnaegoyang.databinding.ActivitySettingBinding
import com.example.dongnaegoyang.databinding.FragmentSettingBinding

private const val TAG = "mmmSearchFragment"

private var _binding: FragmentSettingBinding? = null
private val binding get() = _binding!!

class SettingActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // 툴바 달기
        setSupportActionBar(binding.toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // FrameLayout 설정
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.settingFrame, SettingFragment()).commit()
    }

    // 툴바에서 버튼 클릭 시
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}