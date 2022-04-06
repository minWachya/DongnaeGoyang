package com.example.dongnaegoyang.login.kakaoLogin

import android.content.ContentValues
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.kakao.sdk.auth.AuthApiClient
import com.kakao.sdk.common.model.KakaoSdkError
import com.kakao.sdk.user.UserApiClient

class KakaoLogin(val mContext: Context) {
    //TODO : tempGetKakaoInfo에서 회원정보 가져와서 SharedPreferences에 정보 저장할 것

    var userId = -1L
    var userNickname = "No Value"
    
    
    fun checkKakaoToken(): Boolean {
        Log.d("kakao login", "checkKakaoLogin 시작")
        var result = false
        // 기존 토큰 존재 확인. 존재하면 유효한 토큰인지 확인. 존재하지 않으면 새로 로그인해야 함.
        if (AuthApiClient.instance.hasToken()) {
            UserApiClient.instance.accessTokenInfo { _, error ->
                if (error != null) {
                    if (error is KakaoSdkError && error.isInvalidTokenError() == true) {
                        //로그인 필요 (토큰 유효하지 않음)
                        if(doKakaoLogin()){ //재로그인 성공 시
                            result = true
                        }
                    }
                    else {
                        //기타 에러
                        Log.d("login-checkToken", "error")
                        error.printStackTrace()
                    }
                }
                else {
                    //토큰 존재하면 회원번호로 DB 조회해서 가입된 사용자인지 확인!
                    if(isServiceMember()){
                        result = true //로그인 성공
                    }
                    

                    //토큰 유효성 체크 성공(필요 시 토큰 갱신됨) - 재로그인 불필요, 바로 로그인됨!
                    //아냐 그렇게 하면 안 돼.. 토큰 있어도 서비스 가입했는지 체크해야 돼
/*                    tempGetKakaoUserInfo()
                    //TODO : 타이밍 안맞아서 데이터 안넘어감. how?
                    Log.d("login - doKakaoLogin", "닉네임 : ${userNickname}")
                    val mainIntent = Intent(this, MainActivity::class.java)
                    mainIntent.putExtra("nickname", userNickname)
                    startActivity(mainIntent)
                    finish()

//                    //로그인화면 건너뛰고 홈화면으로 바로 넘어가기
//                    val homeIntent = Intent(this, HomeActivity::class.java)
//                    startActivity(homeIntent)
//                    finish()*/
                }
            }
        }
        else {
            //로그인 필요 (토큰 없음)
            result = doKakaoLogin()
        }
        
        return result
    }

    fun doKakaoLogin():Boolean {
        Log.d("kakao login", "doKakaoLogin 시작")
        var result = false
        // 카카오톡 설치 여부 확인 isKakaoTalkLoginAvailable
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(mContext)) {
            // 카카오톡으로 로그인
            UserApiClient.instance.loginWithKakaoTalk(mContext) { token, error ->
                if (error != null) {
                    Log.e(ContentValues.TAG, "로그인 실패", error)
                }
                else if (token != null) {
                    // 카카오 로그인(토큰 발급) 성공 -> 우리 DB 확인
                    result = isServiceMember()

                    /*Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()
                    Log.i(ContentValues.TAG, "로그인 성공 ${token.accessToken}")
                    isServiceMember()*/
                }
            }
        } else {
            Log.e("로그", "카카오톡이 설치되어있지 않습니다.")
            Toast.makeText(mContext, "카카오톡이 설치되어있지 않습니다.", Toast.LENGTH_SHORT).show()
        }

        return result
    }

    private fun isServiceMember(): Boolean {
        var result = false
        // TODO : Server 카카오회원번호 얻어와서 서비스에 회원가입 되어있는(우리 DB에 있는) 유저인지 확인
        if(checkMemberInfo()) { //서비스 가입된 유저일 경우
            // 로그인 됨, 메인화면으로 이동
            result = true
        }
        else{
            //서비스 미가입 유저 - 원래 화면으로 false 반환
        }

        return result
    }

    private fun checkMemberInfo(): Boolean {
        // TODO : Server : 카카오회원번호를 우리 DB에 조회해서 가입된 회원이면 회원정보를 응답받고 true반환. 회원이 아니면 false반환.
        //임시 사용 코드
        var isMember = tempGetKakaoUserInfo()
        return false
    }

    private fun tempGetKakaoUserInfo(): Boolean {
        // 사용자 정보 요청 (기본)
        UserApiClient.instance.me { user, error ->
            if (error != null) {
                Log.e(ContentValues.TAG, "사용자 정보 요청 실패", error)
            }
            else if (user != null) {
                userNickname = user.kakaoAccount?.profile?.nickname ?: "Get No Value"
                userId = user.id ?: -2L

                Log.i(
                    ContentValues.TAG, "사용자 정보 요청 성공" +
                            "\n회원번호: ${user.id}" +
                            "\n닉네임: ${user.kakaoAccount?.profile?.nickname}" )
//                Toast.makeText(mContext, "${userNickname}님 카카오 계정 정보 가져오기 성공.", Toast.LENGTH_SHORT).show()
            }
        }
        return false //TODO : 회원가입 테스트하려고 무조건 회원 아니게 함
    }

}