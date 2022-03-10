package com.example.dongnaegoyang.login.kakaoInit

import android.app.Application
import android.content.ContentValues.TAG
import android.util.Log
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.util.Utility

class InitApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // Kakao SDK 초기화
        KakaoSdk.init(this, "60d315c94bbadde870d7af4baa18d52d")

        /*//카카오 SDK 사용해서 디버그 키 해시값 얻기
        var keyHash = Utility.getKeyHash(this)
        Log.e(TAG, "해시 키 값 : ${keyHash}")*/
    }
}