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
    
//
//    fun checkKakaoToken(): Boolean {
//        Log.d("jh checkKakaoToken", "시작")
//        var result = false
//        // 기존 토큰 존재 확인. 존재하면 유효한 토큰인지 확인. 존재하지 않으면 새로 로그인해야 함.
//        if (AuthApiClient.instance.hasToken()) {
//            UserApiClient.instance.accessTokenInfo { tokenInfo, error ->
//                if (error != null) {
//                    if (error is KakaoSdkError && error.isInvalidTokenError()) {
//                        Log.d("jh checkKakaoLogin", "로그인 필요 (토큰 유효하지 않음)")
//                        //로그인 필요 (토큰 유효하지 않음)
//                        if(doKakaoLogin()){ //재로그인 성공 시
//                            result = true
//                        }
//                    }
//                    else {
//                        //기타 에러
//                        Log.d("jh login-checkToken", "error")
//                        error.printStackTrace()
//                    }
//                }
//                else {
//                    Log.d("jh checkKakaoLogin", "토큰 존재 - DB 조회해서 가입된 사용자인지 확인")
//                    //토큰 존재하면 회원번호로 DB 조회해서 가입된 사용자인지 확인!
//                    Log.d("jh", "tokenInfo.id : ${tokenInfo?.id}")
//
////                    result = isServiceMember() //로그인 성공
//
//
//                    //토큰 유효성 체크 성공(필요 시 토큰 갱신됨) - 재로그인 불필요, 바로 로그인됨!
//                    //아냐 그렇게 하면 안 돼.. 토큰 있어도 서비스 가입했는지 체크해야 돼
///*                    tempGetKakaoUserInfo()
//                    //TODO : 타이밍 안맞아서 데이터 안넘어감. how?
//                    Log.d("login - doKakaoLogin", "닉네임 : ${userNickname}")
//                    val mainIntent = Intent(this, MainActivity::class.java)
//                    mainIntent.putExtra("nickname", userNickname)
//                    startActivity(mainIntent)
//                    finish()
//
////                    //로그인화면 건너뛰고 홈화면으로 바로 넘어가기
////                    val homeIntent = Intent(this, HomeActivity::class.java)
////                    startActivity(homeIntent)
////                    finish()*/
//                }
//            }
//        }
//        else {
//            //로그인 필요 (토큰 없음)
//            result = doKakaoLogin()
//        }
//
//        return result
//    }
//
//    //토큰이 없을 때, 있는데 유효하지 않을 때 - 로그인 진행
//    fun doKakaoLogin():Boolean {
//        Log.d("jh kakao login", "doKakaoLogin 시작")
//        var result = false
//        // 카카오톡 설치 여부 확인 isKakaoTalkLoginAvailable
//        if (UserApiClient.instance.isKakaoTalkLoginAvailable(mContext)) {
//            // 카카오톡으로 로그인
//            UserApiClient.instance.loginWithKakaoTalk(mContext) { token, error ->
//                if (error != null) {
//                    Log.e("jh", "로그인 실패", error)
//                }
//                else if (token != null) {
//                    // 카카오 로그인(토큰 발급) 성공 -> 우리 DB 확인
//                    Log.i("jh", "로그인 성공 ${token.accessToken}")
//                    result = true //임시
////                    result = isServiceMember()
//
//                    /*Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()
//                    Log.i("jh", "로그인 성공 ${token.accessToken}")
//                    isServiceMember()*/
//                }
//            }
//        } else {
//            Log.e("jh 로그", "카카오톡이 설치되어있지 않습니다.")
//            Toast.makeText(mContext, "카카오톡이 설치되어있지 않습니다.", Toast.LENGTH_SHORT).show()
//        }
//
//        return result
//    }

    // 카카오 회원번호 받아오고, 그걸로 DB 조회해서 가입된 사용자인지 확인. 가입된 사용자면 로그인시키고 회원정보받아옴. 가입 안되어있으면 403같은 에러 받아오고 프론트에서 회원가입 화면으로 넘김.
    private fun isServiceMember(): Boolean {
        Log.d("jh isServiceMember", "시작.")

        var result = false
        // 카카오회원번호 받아오기
        // 사용자 정보 요청 (기본)
        UserApiClient.instance.me { user, error ->
            if (error != null) {
                Log.e("jh checkMemberInfo", "사용자 정보 요청 실패", error)
            }
            else if (user != null) {
                userNickname = user.kakaoAccount?.profile?.nickname ?: "Get No Value"
                userId = user.id ?: -2L

                Log.d(
                    "jh checkMemberInfo", "사용자 정보 요청 성공. isMember==true" +
                            "\n회원번호: ${userId}" +
                            "\n닉네임: ${userNickname}" )
//                Toast.makeText(mContext, "${userNickname}님 카카오 계정 정보 가져오기 성공.", Toast.LENGTH_SHORT).show()


                //TODO : Server : 얻어온 카카오 회원번호로 우리 DB에 가입된 회원인지 조회하는 내용 - 결과로 isMember 반환
                // ...
                var isMember = true //TODO : 회원가입 테스트하려고 회원인 걸로 가정함

                //서버 확인 결과에 따른 처리
                if(isMember) { //서비스 가입된 유저일 경우
                    Log.d("jh isServiceMember", "true. 가입된 사용자. 로그인 성공")
                    // 로그인 됨, 메인화면으로 이동
                    result = true
                }
                else{
                    Log.d("jh isServiceMember", "false. 미가입 사용자.")
                    //서비스 미가입 유저 - 원래 화면으로 false 반환
                }

            }
        }


        return result
    }

/*    private fun checkMemberInfo(): Boolean {
        // TODO : Server : 카카오회원번호를 우리 DB에 조회해서 가입된 회원이면 회원정보를 응답받고 true반환. 회원이 아니면 false반환.
        Log.d("jh checkMemberInfo", "시작")

        return isMember
    }*/

}