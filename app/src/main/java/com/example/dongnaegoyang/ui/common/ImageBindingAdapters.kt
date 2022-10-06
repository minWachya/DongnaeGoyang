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
    view.setImageResource(CustomCatArr.arrImgFur[size][fur])
}
// 귀: arrImgEar[fur][ear]
@BindingAdapter("CustomCatFur", "CustomCatEar")
fun applyEar(view: ImageView, fur: Int, ear: Int) {
    view.setImageResource(CustomCatArr.arrImgEar[fur][ear])
}
// 꼬리: arrImgTail[fur][tail]
@BindingAdapter("CustomCatFur", "CustomCatTail")
fun applyTail(view: ImageView, fur: Int, tail: Int) {
    view.setImageResource(CustomCatArr.arrImgTail[fur][tail])
}
// 몸집: arrImgSize[ear][size]
@BindingAdapter("CustomCatEar", "CustomCatSize")
fun applySize(view: ImageView, ear: Int, size: Int) {
    view.setImageResource(CustomCatArr.arrImgSize[ear][size])
}
// 수염: arrImgWhisker[fur][whisker]
@BindingAdapter("CustomCatFur", "CustomCatWhisker")
fun applyWhisker(view: ImageView, fur: Int, whisker: Int) {
    view.setImageResource(CustomCatArr.arrImgWhisker[fur][whisker])
}