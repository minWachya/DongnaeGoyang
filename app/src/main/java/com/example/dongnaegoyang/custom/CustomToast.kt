package com.example.dongnaegoyang.custom

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import com.example.dongnaegoyang.R

// 커스텀 토스트
object CustomToast {
    fun Toast.showCustomToast(message: String, context: Context) {
        val layout = LayoutInflater.from(context).inflate(R.layout.custom_toast,null)
        val text : TextView = layout.findViewById(R.id.textToast)
        text.text = message
        this.apply {
            setGravity(Gravity.BOTTOM, 0, 80)
            duration = Toast.LENGTH_SHORT
            view = layout
            show()
        }
    }
}