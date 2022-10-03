package com.example.dongnaegoyang

import android.app.Application
import android.content.Context
import com.example.dongnaegoyang.login.kakaoLogin.InitApplication
import com.kakao.sdk.common.KakaoSdk
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication: Application() {

    init {
        instance = this
    }

    companion object {
        lateinit var instance: MyApplication

        //어디서든 context 사용 가능(이걸로 SharedPreference 의 토큰 가져다 씀)
        fun getApplicationContext(): Context {
            return instance.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()

        // Kakao SDK 초기화
        KakaoSdk.init(this, "60d315c94bbadde870d7af4baa18d52d")

        //카카오 SDK 사용해서 디버그 키 해시값 얻기
//        var keyHash = Utility.getKeyHash(this)
//        Log.e(TAG, "해시 키 값 : ${keyHash}")
    }
}