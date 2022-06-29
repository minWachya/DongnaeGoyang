package com.example.dongnaegoyang.common

import com.example.dongnaegoyang.R

// 코숏, 몸집, 귀, 꼬리, 수염
data class CustomCat(val fur: Int, val size: Int, val ear: Int, val tail: Int, val whisker: Int)

/*
몸집 배열: arrImgSize[ear][size]
코숏 배열: arrImgFur[size][fur]
귀 배열: arrImgEar[fur][ear]
꼬리 배열: arrImgTail[fur][tail]
수염 배열: arrImgWhisker[fur][whisker]
*/

object CustomCatArr {
    // 몸집 이미지 배열
    // 기본 몸집 이미지 배열: 마름, 보통, 뚱뚱, 기본
    private val baseSize = listOf(R.drawable.ic_size_small, R.drawable.ic_size_normal, R.drawable.ic_size_large, R.drawable.ic_size_normal)
    // 귀가 fold 일 때의 몸집 이미지
    private val foldEarSize = listOf(R.drawable.ic_size_small_no_line, R.drawable.ic_size_normal_no_line, R.drawable.ic_size_large_no_line, R.drawable.ic_size_normal)
    // 기본, 폴드, tnr
    val arrImgSize = listOf(baseSize, foldEarSize, baseSize)

    // 코숏 이미지 배열
    // 마름 SKINNY: 치즈, 올블랙, 고등어, 삼색, 턱시도, 카오스, 점박이, 기본
    private val furSkinny = listOf(R.drawable.ic_fur_cheese_s, R.drawable.ic_fur_black_s, R.drawable.ic_fur_mackerel_s,
        R.drawable.ic_fur_three_colors_s, R.drawable.ic_fur_tuxedo_s, R.drawable.ic_fur_chaos_s, R.drawable.ic_fur_spot_s, R.drawable.ic_cat_fur_base)
    // 보통 NORMAL: 치즈, 올블랙, 고등어, 삼색, 턱시도, 카오스, 점박이, 기본
    private val furNormal =  listOf(R.drawable.ic_fur_cheese, R.drawable.ic_fur_black, R.drawable.ic_fur_mackerel,
        R.drawable.ic_fur_three_colors, R.drawable.ic_fur_tuxedo, R.drawable.ic_fur_chaos, R.drawable.ic_fur_spot, R.drawable.ic_cat_fur_base)
    // 뚱뚱 FAT: 치즈, 올블랙, 고등어, 삼색, 턱시도, 카오스, 점박이, 기본
    private val furFat = listOf(R.drawable.ic_fur_cheese_l, R.drawable.ic_fur_black_l, R.drawable.ic_fur_mackerel_l,
        R.drawable.ic_fur_three_colors_l, R.drawable.ic_fur_tuxedo_l, R.drawable.ic_fur_chaos_l, R.drawable.ic_fur_spot_l, R.drawable.ic_cat_fur_base)
    val arrImgFur = listOf(furSkinny, furNormal, furFat, furNormal)

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
        listOf(R.drawable.ic_ear_open_spot, R.drawable.ic_ear_fold_spot, R.drawable.ic_ear_tnr_spot),
        // 기본
        listOf(R.drawable.ic_cat_ear_base, R.drawable.ic_ear_fold_spot, R.drawable.ic_cat_ear_base_tnr)
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
        listOf(R.drawable.ic_tail_long_spot, R.drawable.ic_tail_short_spot),
        // 기본
        listOf(R.drawable.ic_tail_long_cheese, R.drawable.ic_tail_short_cheese)
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