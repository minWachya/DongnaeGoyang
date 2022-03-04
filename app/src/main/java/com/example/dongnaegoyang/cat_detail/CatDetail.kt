package com.example.dongnaegoyang.cat_detail

import com.example.dongnaegoyang.R

// 코숏, 몸집, 귀, 꼬리, 수염
data class CatDetail(val fur: Int, val size: Int, val ear: Int, val tail: Int, val whisker: Int)

object CatDetailArr {
    // 몸집 이미지 배열: 마름, 보통, 뚱뚱
    val arrImgSize = listOf(R.drawable.ic_size_normal, R.drawable.ic_size_normal, R.drawable.ic_size_normal)
    // 코숏 이미지 배열
    val arrImgFur = listOf(
        // 마름 SKINNY: 치즈, 올블랙, 고등어, 삼색, 턱시도, 카오스, 점박이
        listOf(R.drawable.ic_fur_cheese, R.drawable.ic_fur_black, R.drawable.ic_fur_mackerel,
            R.drawable.ic_fur_three_colors, R.drawable.ic_fur_tuxedo, R.drawable.ic_fur_chaos, R.drawable.ic_fur_spot),
        // 보통 NORMAL: 치즈, 올블랙, 고등어, 삼색, 턱시도, 카오스, 점박이
        listOf(R.drawable.ic_fur_cheese, R.drawable.ic_fur_black, R.drawable.ic_fur_mackerel,
            R.drawable.ic_fur_three_colors, R.drawable.ic_fur_tuxedo, R.drawable.ic_fur_chaos, R.drawable.ic_fur_spot),
        // 뚱뚱 FAT: 치즈, 올블랙, 고등어, 삼색, 턱시도, 카오스, 점박이
        listOf(R.drawable.ic_fur_cheese, R.drawable.ic_fur_black, R.drawable.ic_fur_mackerel,
            R.drawable.ic_fur_three_colors, R.drawable.ic_fur_tuxedo, R.drawable.ic_fur_chaos, R.drawable.ic_fur_spot)
    )
    // 귀 이미지 배열
    val arrImgEar = listOf(
        // 치즈 CHEESE: open, fold, tnr
        listOf(R.drawable.ic_ear_open_cheese, R.drawable.ic_ear_fold_cheese, R.drawable.ic_ear_tnr_cheese),
        // 올블랙 BLACK: open, fold, tnr
        listOf(R.drawable.ic_ear_open_black, R.drawable.ic_ear_fold_black, R.drawable.ic_ear_tnr_black),
        // 고등어 MACKEREL: open, fold, tnr
        listOf(R.drawable.ic_ear_open_mackerel, R.drawable.ic_ear_fold_mackerel, R.drawable.ic_ear_tnr_mackerel),
        // 삼색 THREE_COLORS: open, fold, tnr
        listOf(R.drawable.ic_ear_open_three_colors, R.drawable.ic_ear_fold_three_colors, R.drawable.ic_ear_tnr_three_colors),
        // 턱시도 TUXEDO: open, fold, tnr
        listOf(R.drawable.ic_ear_open_tuxedo, R.drawable.ic_ear_fold_tuxedo, R.drawable.ic_ear_tnr_tuxedo),
        // 카오스 CHAOS: open, fold, tnr
        listOf(R.drawable.ic_ear_open_chaos, R.drawable.ic_ear_fold_chaos, R.drawable.ic_ear_tnr_chaos),
        // 얼룩 SPOT: open, fold, tnr
        listOf(R.drawable.ic_ear_open_spot, R.drawable.ic_ear_fold_spot, R.drawable.ic_ear_tnr_spot)
    )
    // 꼬리 이미지 배열
    val arrImgTail = listOf(
        // 치즈 CHEESE: long, short
        listOf(R.drawable.ic_tail_long_cheese, R.drawable.ic_tail_short_cheese),
        // 올블랙 BLACK: long, short
        listOf(R.drawable.ic_tail_long_black, R.drawable.ic_tail_short_black),
        // 고등어 MACKEREL: long, short
        listOf(R.drawable.ic_tail_long_mackerel, R.drawable.ic_tail_short_mackerel),
        // 삼색 THREE_COLORS: long, short
        listOf(R.drawable.ic_tail_long_three_colors, R.drawable.ic_tail_short_three_colors),
        // 턱시도 TUXEDO: long, short
        listOf(R.drawable.ic_tail_long_tuxedo, R.drawable.ic_tail_short_tuxedo),
        // 카오스 CHAOS: long, short
        listOf(R.drawable.ic_tail_long_chaos, R.drawable.ic_tail_short_chaos),
        // 얼룩 SPOT: long, short
        listOf(R.drawable.ic_tail_long_spot, R.drawable.ic_tail_short_spot)
    )
    // 수염 이미지 배열
    val arrImgWhisker = listOf(
        listOf(R.drawable.ic_whisker_short, R.drawable.ic_whisker_long),    // 치즈: 검은 수염
        listOf(R.drawable.ic_whisker_short_w, R.drawable.ic_whisker_long_w),// 올블랙: 흰 수염
        listOf(R.drawable.ic_whisker_short, R.drawable.ic_whisker_long),    // 고등어: 검은 수염
        listOf(R.drawable.ic_whisker_short, R.drawable.ic_whisker_long),    // 삼색: 검은 수염
        listOf(R.drawable.ic_whisker_short, R.drawable.ic_whisker_long),    // 턱시도: 검은 수염
        listOf(R.drawable.ic_whisker_short_w, R.drawable.ic_whisker_long_w),// 카오스: 흰 수염
        listOf(R.drawable.ic_whisker_short, R.drawable.ic_whisker_long),    // 얼룩: 검은 수염
    )
}