package com.example.dongnaegoyang

import android.content.Context

class AssetLoader(private val context: Context) {
    // 오류 처리 포함
    fun getJsonString(fileName: String): String? {
        // 성공, 실패로 나뉘는 작업 처리: runCatching
        // 성공 시 result, 실패 시 null 반환
        return kotlin.runCatching {
            loadAsset(fileName)
        }.getOrNull()
    }

    // 자원 읽어오기
    private fun loadAsset(fileName: String): String {
        return context.assets.open(fileName).use { inputStream ->
            val size = inputStream.available()  // inputStream에 data 실제로 존재하는지?
            val bytes = ByteArray(size)         // 크기가 sizw인 ByteArray 생성
            inputStream.read(bytes)             // inputStream의 ByteArray를 bytes 객체에 복사
            String(bytes)                       // String으로 변경하여 리턴
        }
    }
}