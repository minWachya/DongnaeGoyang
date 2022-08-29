package com.example.dongnaegoyang.ui.common

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.dongnaegoyang.R

// 고양이 상세: TNR 접종 여부
@BindingAdapter("applyTNR")
fun applyTNR(view: TextView, index: Int) {
    view.text = when(index){
        1 -> "접종 완료"
        2 -> "접종 미완료"
        else -> "모름"
    }
}

// 고양이 상세: 비선호 사료 여부
@BindingAdapter("applyFeed")
fun applyFeed(view: TextView, index: Int) {
    val arrFeed = view.context.resources.getStringArray(R.array.cat_add3_food_array)
    view.text = arrFeed[index].toString()
}