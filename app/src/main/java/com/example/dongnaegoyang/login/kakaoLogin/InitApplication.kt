package com.example.dongnaegoyang.login.kakaoLogin

import android.app.Application
import android.content.Context
import android.util.Log
import com.example.dongnaegoyang.cat_search.SearchCatFragment.Companion.TAG
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.util.Utility

class InitApplication : Application() {
    init {
        instance = this
    }

    companion object {
        lateinit var instance: InitApplication

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