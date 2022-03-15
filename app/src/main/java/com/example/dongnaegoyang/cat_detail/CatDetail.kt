package com.example.dongnaegoyang.cat_detail

import com.example.dongnaegoyang.R

// 코숏, 몸집, 귀, 꼬리, 수염
data class CatDetail(val fur: Int, val size: Int, val ear: Int, val tail: Int, val whisker: Int)

object CatDetailArr {
    // 몸집 이미지 배열: 마름, 보통, 뚱뚱, 기본
    val arrImgSize = listOf(R.drawable.ic_size_small, R.drawable.ic_size_normal, R.drawable.ic_size_large, R.drawable.ic_size_normal)

    // 코숏 이미지 배열
    // 마름 SKINNY: 치즈, 올블랙, 고등어, 삼색, 턱시도, 카오스, 점박이, 기본
    private val furSkinny = listOf(R.drawable.ic_fur_cheese_s, R.drawable.ic_fur_black_s, R.drawable.ic_fur_mackerel_s, R.drawable.ic_fur_three_colors_s,
        R.drawable.ic_fur_tuxedo_s, R.drawable.ic_fur_chaos_s, R.drawable.ic_fur_spot_s, R.drawable.ic_cat_fur_base)
    // 보통 NORMAL: 치즈, 올블랙, 고등어, 삼색, 턱시도, 카오스, 점박이, 기본
    private val furNormal =  listOf(R.drawable.ic_fur_cheese, R.drawable.ic_fur_black, R.drawable.ic_fur_mackerel, R.drawable.ic_fur_three_colors,
        R.drawable.ic_fur_tuxedo, R.drawable.ic_fur_chaos, R.drawable.ic_fur_spot, R.drawable.ic_cat_fur_base)
    // 뚱뚱 FAT: 치즈, 올블랙, 고등어, 삼색, 턱시도, 카오스, 점박이, 기본
    private val furFat = listOf(R.drawable.ic_fur_cheese_l, R.drawable.ic_fur_black_l, R.drawable.ic_fur_mackerel_l, R.drawable.ic_fur_three_colors_l,
        R.drawable.ic_fur_tuxedo_l, R.drawable.ic_fur_chaos_l, R.drawable.ic_fur_spot_l, R.drawable.ic_cat_fur_base)
    val arrImgFur = listOf(furSkinny, furNormal, furFat, furNormal)

    // 귀 이미지 배열
    val arrImgEar = listOf(
        // 치즈 CHEESE: open, fold, tnr, 기본
        listOf(R.drawable.ic_ear_open_cheese, R.drawable.ic_ear_fold_cheese, R.drawable.ic_ear_tnr_cheese, R.drawable.ic_cat_ear_base),
        // 올블랙 BLACK: open, fold, tnr, 기본
        listOf(R.drawable.ic_ear_open_black, R.drawable.ic_ear_fold_black, R.drawable.ic_ear_tnr_black, R.drawable.ic_cat_ear_base),
        // 고등어 MACKEREL: open, fold, tnr, 기본
        listOf(R.drawable.ic_ear_open_mackerel, R.drawable.ic_ear_fold_mackerel, R.drawable.ic_ear_tnr_mackerel, R.drawable.ic_cat_ear_base),
        // 삼색 THREE_COLORS: open, fold, tnr, 기본
        listOf(R.drawable.ic_ear_open_three_colors, R.drawable.ic_ear_fold_three_colors, R.drawable.ic_ear_tnr_three_colors, R.drawable.ic_cat_ear_base),
        // 턱시도 TUXEDO: open, fold, tnr, 기본
        listOf(R.drawable.ic_ear_open_tuxedo, R.drawable.ic_ear_fold_tuxedo, R.drawable.ic_ear_tnr_tuxedo, R.drawable.ic_cat_ear_base),
        // 카오스 CHAOS: open, fold, tnr, 기본
        listOf(R.drawable.ic_ear_open_chaos, R.drawable.ic_ear_fold_chaos, R.drawable.ic_ear_tnr_chaos, R.drawable.ic_cat_ear_base),
        // 얼룩 SPOT: open, fold, tnr, 기본
        listOf(R.drawable.ic_ear_open_spot, R.drawable.ic_ear_fold_spot, R.drawable.ic_ear_tnr_spot, R.drawable.ic_cat_ear_base),
        // 기본
        listOf(R.drawable.ic_ear_open_spot, R.drawable.ic_ear_fold_spot, R.drawable.ic_ear_tnr_spot, R.drawable.ic_cat_ear_base)
    )
    // 꼬리 이미지 배열
    val arrImgTail = listOf(
        // 치즈 CHEESE: long, short, 기본
        listOf(R.drawable.ic_tail_long_cheese, R.drawable.ic_tail_short_cheese, R.drawable.ic_cat_tail_base),
        // 올블랙 BLACK: long, short, 기본
        listOf(R.drawable.ic_tail_long_black, R.drawable.ic_tail_short_black, R.drawable.ic_cat_tail_base),
        // 고등어 MACKEREL: long, short, 기본
        listOf(R.drawable.ic_tail_long_mackerel, R.drawable.ic_tail_short_mackerel, R.drawable.ic_cat_tail_base),
        // 삼색 THREE_COLORS: long, short, 기본
        listOf(R.drawable.ic_tail_long_three_colors, R.drawable.ic_tail_short_three_colors, R.drawable.ic_cat_tail_base),
        // 턱시도 TUXEDO: long, short, 기본
        listOf(R.drawable.ic_tail_long_tuxedo, R.drawable.ic_tail_short_tuxedo, R.drawable.ic_cat_tail_base),
        // 카오스 CHAOS: long, short, 기본
        listOf(R.drawable.ic_tail_long_chaos, R.drawable.ic_tail_short_chaos, R.drawable.ic_cat_tail_base),
        // 얼룩 SPOT: long, short, 기본
        listOf(R.drawable.ic_tail_long_spot, R.drawable.ic_tail_short_spot, R.drawable.ic_cat_tail_base),
        // 기본
        listOf(R.drawable.ic_tail_long_cheese, R.drawable.ic_tail_short_cheese, R.drawable.ic_cat_tail_base)
    )
    // 수염 이미지 배열
    private val blackWhisker = listOf(R.drawable.ic_whisker_short, R.drawable.ic_whisker_long, R.drawable.ic_cat_whisker_base)
    private val whiteWhisker = listOf(R.drawable.ic_whisker_short_w, R.drawable.ic_whisker_long_w, R.drawable.ic_cat_whisker_base)
    val arrImgWhisker = listOf(
        blackWhisker,    // 치즈: 검은 수염
        whiteWhisker,    // 올블랙: 흰 수염
        blackWhisker,    // 고등어: 검은 수염
        blackWhisker,    // 삼색: 검은 수염
        blackWhisker,    // 턱시도: 검은 수염
        whiteWhisker,    // 카오스: 흰 수염
        blackWhisker,    // 얼룩: 검은 수염
        blackWhisker     // 기본: 검은 수염
    )
}