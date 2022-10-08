package com.example.dongnaegoyang.ui.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.dongnaegoyang.common.CustomCatArr

// 커스텀 속성 적용

// url 이미지
@BindingAdapter("loadImage")
fun loadImage(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(view)
            .load(imageUrl)
            .into(view)
    }
}

// 코숏: arrImgFur[size][fur]
@BindingAdapter("CustomCatSize", "CustomCatFur")
fun applyFur(view: ImageView, size: Int, fur: Int) {
    try {
        view.setImageResource(CustomCatArr.arrImgFur[size][fur])
    } catch(e: IndexOutOfBoundsException) {
        view.setImageResource(CustomCatArr.arrImgFur[0][0])
    }
}
// 귀: arrImgEar[fur][ear]
@BindingAdapter("CustomCatFur", "CustomCatEar")
fun applyEar(view: ImageView, fur: Int, ear: Int) {
    try {
        view.setImageResource(CustomCatArr.arrImgEar[fur][ear])
    } catch(e: IndexOutOfBoundsException) {
        view.setImageResource(CustomCatArr.arrImgEar[0][0])
    }
}
// 꼬리: arrImgTail[fur][tail]
@BindingAdapter("CustomCatFur", "CustomCatTail")
fun applyTail(view: ImageView, fur: Int, tail: Int) {
    try {
        view.setImageResource(CustomCatArr.arrImgTail[fur][tail])
    } catch(e: IndexOutOfBoundsException) {
        view.setImageResource(CustomCatArr.arrImgTail[0][0])
    }
}
// 몸집: arrImgSize[ear][size]
@BindingAdapter("CustomCatEar", "CustomCatSize")
fun applySize(view: ImageView, ear: Int, size: Int) {
    try {
        view.setImageResource(CustomCatArr.arrImgSize[ear][size])
    } catch(e: IndexOutOfBoundsException) {
        view.setImageResource(CustomCatArr.arrImgSize[0][0])
    }
}
// 수염: arrImgWhisker[fur][whisker]
@BindingAdapter("CustomCatFur", "CustomCatWhisker")
fun applyWhisker(view: ImageView, fur: Int, whisker: Int) {
    try {
        view.setImageResource(CustomCatArr.arrImgWhisker[fur][whisker])
    } catch(e: IndexOutOfBoundsException) {
        view.setImageResource(CustomCatArr.arrImgWhisker[0][0])
    }
}