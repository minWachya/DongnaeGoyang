package com.example.dongnaegoyang.ui.common

import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

// 고양이 상세: 게시글 작성 시간
@BindingAdapter("applyDateFormat")
fun applyDateFormat(view: TextView, dateStr: String) {
    view.text = getTimeAgo(dateStr)
}

fun getTimeAgo(timeStr: String): String {
    val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
    val pasTime = format.parse(timeStr)

    val nowTime = Date()
    val dateDiff = nowTime.time - pasTime.time
    val second = TimeUnit.MILLISECONDS.toSeconds(dateDiff)
    val minute = TimeUnit.MILLISECONDS.toMinutes(dateDiff)
    val hour = TimeUnit.MILLISECONDS.toHours(dateDiff)
    val day = TimeUnit.MILLISECONDS.toDays(dateDiff)

    return if (second < 60) "${second}초 전"
    else if (minute < 60)  "${minute}분 전"
    else if (hour < 24) "${hour}시간 전"
    else if (day < 7) "${day}일 전"
    else {
        val simpleFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        simpleFormat.format(pasTime)
    }
}
