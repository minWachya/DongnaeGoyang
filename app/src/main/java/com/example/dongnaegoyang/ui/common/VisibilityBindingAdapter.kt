package com.example.dongnaegoyang.ui.common

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter

// 주소 검색: address2, 3 존재 여부에 따른 '>' visibility 변경
@BindingAdapter("isExistAddress")
fun isExistAddress(view: TextView, text: String?) {
    view.visibility = if (!text.isNullOrEmpty()) View.VISIBLE else View.INVISIBLE
}

// 고양이 상세: 수정 버튼
@BindingAdapter("isVisible")
fun isVisible(view: ImageView, bool: Boolean) {
    view.visibility = if (bool) View.VISIBLE else View.INVISIBLE
}