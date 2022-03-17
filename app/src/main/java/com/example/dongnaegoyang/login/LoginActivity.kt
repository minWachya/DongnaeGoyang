package com.example.dongnaegoyang.login

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.dongnaegoyang.databinding.ActivityLoginBinding
import com.example.dongnaegoyang.home.MainActivity
import com.kakao.sdk.auth.AuthApiClient
import com.kakao.sdk.common.model.KakaoSdkError
import com.kakao.sdk.user.UserApiClient

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    var userNickname = "No Value"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        binding.btnLoginFinish.setOnClickListener {
            finish()
        }

        binding.btnKakaoLogin.setOnClickListener {
            checkKakaoToken()
        }


    }

    private fun checkKakaoToken() {
        // 기존 토큰 존재 확인. 존재하면 유효한 토큰인지 확인. 존재하지 않으면 새로 로그인해야 함.
        if (AuthApiClient.instance.hasToken()) {
            UserApiClient.instance.accessTokenInfo { _, error ->
                if (error != null) {
                    if (error is KakaoSdkError && error.isInvalidTokenError() == true) {
                        //로그인 필요 (토큰 유효하지 않음)
                        doKakaoLogin()
                    }
                    else {
                        //기타 에러
                        Log.d("login-checkToken", "error")
                        error.printStackTrace()
                    }
                }
                else {
                    //토큰 유효성 체크 성공(필요 시 토큰 갱신됨) - 재로그인 불필요, 바로 로그인됨!
                    tempGetKakaoUserInfo()
                    //TODO : 타이밍 안맞아서 데이터 안넘어감. how?
                    Log.d("login - doKakaoLogin", "닉네임 : ${userNickname}")
                    val mainIntent = Intent(this, MainActivity::class.java)
                    mainIntent.putExtra("nickname", userNickname)
                    startActivity(mainIntent)
                    finish()

                    /*//로그인화면 건너뛰고 홈화면으로 바로 넘어가기
                    val homeIntent = Intent(this, HomeActivity::class.java)
                    startActivity(homeIntent)
                    finish()*/
                }
            }
        }
        else {
            //로그인 필요 (토큰 없음)
            doKakaoLogin()
        }


    }

    private fun doKakaoLogin() {
        // 카카오톡 설치 여부 확인 isKakaoTalkLoginAvailable
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
            // 카카오톡으로 로그인
            UserApiClient.instance.loginWithKakaoTalk(this) { token, error ->
                if (error != null) {
                    Log.e(ContentValues.TAG, "로그인 실패", error)
                }
                else if (token != null) {
                    // 카카오 로그인(토큰 발급) 성공
                    Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()
                    Log.i(ContentValues.TAG, "로그인 성공 ${token.accessToken}")

                    // 회원번호 얻어와서 회원가입 되어있는(우리 DB에 있는) 유저인지 확인
                    if(checkMemberInfo()) { //가입된 유저일 경우
                        // 로그인 됨, 메인화면으로 이동
                        //TODO : 타이밍 안맞아서 데이터 안넘어감. how?
                        Log.d("login - doKakaoLogin", "닉네임 : ${userNickname}")
                        val mainIntent = Intent(this, MainActivity::class.java)
                        mainIntent.putExtra("nickname", userNickname)
                        startActivity(mainIntent)
                        finish()
                    }
                    else{ //미가입 유저
                        // TODO : 회원가입 다이얼로그 -> 회원가입 하겠다고 하면 동네설정 화면으로 이동.
                        Toast.makeText(this, "${userNickname}님은 가입되지 않은 사용자입니다. 회원가입이 필요합니다.", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        } else {
            Log.e("로그", "카카오 로그인이 불가합니다.")
        }
    }

    private fun checkMemberInfo(): Boolean {
        // TODO : Server : 회원이면 회원정보를 응답받고 true반환. 회원이 아니면 아니라는 메세지만 응답받고 false반환.
        //임시로 사용하기 위한 닉네임 가져오기 코드
        var isMember = tempGetKakaoUserInfo()
        return isMember
    }

    private fun tempGetKakaoUserInfo(): Boolean {
        // 사용자 정보 요청 (기본)
        UserApiClient.instance.me { user, error ->
            if (error != null) {
                Log.e(ContentValues.TAG, "사용자 정보 요청 실패", error)
            }
            else if (user != null) {
                Log.i(
                    ContentValues.TAG, "사용자 정보 요청 성공" +
                        "\n회원번호: ${user.id}" +
                        "\n닉네임: ${user.kakaoAccount?.profile?.nickname}" )
                userNickname = user.kakaoAccount?.profile?.nickname ?: "No Value"
                Toast.makeText(this, "${userNickname}님 카카오 계정 정보 가져오기 성공.", Toast.LENGTH_SHORT).show()


            }
        }
        return true
    }
}