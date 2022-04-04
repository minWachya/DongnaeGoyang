package com.example.dongnaegoyang.login

import android.app.Activity
import android.content.ContentValues
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import com.example.dongnaegoyang.R
import com.example.dongnaegoyang.address_search.SearchAddressActivity
import com.example.dongnaegoyang.databinding.ActivityLoginBinding
import com.example.dongnaegoyang.home.MainActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.kakao.sdk.auth.AuthApiClient
import com.kakao.sdk.common.model.KakaoSdkError
import com.kakao.sdk.user.UserApiClient

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var resultLauncher : ActivityResultLauncher<Intent>
    var userId = -1L
    var userNickname = "No Value"
    var address = arrayListOf<String>("No Value", "No Value", "No Value")

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

        val signUpView = layoutInflater.inflate(R.layout.activity_sign_up_dialog, null)
        val signUpDialog = BottomSheetDialog(this, R.style.DialogCustomTheme)
        signUpDialog.setContentView(signUpView)
        binding.tvLoginSignUp.setOnClickListener {
            signUpDialog.show()
        }

        resultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) { result ->
            Log.d("login address", "launcher 실행")
            if (result.resultCode == Activity.RESULT_OK) {
                //TODO : 주소 받아오는 거 아직 작동 안함
                address[0] = result.data?.getStringExtra("si") ?: "Get No Value"
                address[1] = result.data?.getStringExtra("gu") ?: "Get No Value"
                address[2] = result.data?.getStringExtra("dong") ?: "Get No Value"
                Log.d("login address", "주소 : ${address[0]}, ${address[1]}, ${address[2]}")
                Toast.makeText(applicationContext, "회원가입 요청 중입니다.", Toast.LENGTH_SHORT).show()
                postSignUp(userId, address)
            }
            else{
                Log.d("login address", "launcher RESULT OK 아님")
                Log.d("login address", "launcher RESULT : ${result.resultCode}")

            }
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
                    //토큰 존재하면 회원번호로 DB 조회해서 가입된 사용자인지 확인!
                    isServiceMember()

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
                    isServiceMember()
                }
            }
        } else {
            Log.e("로그", "카카오톡이 설치되어있지 않습니다.")
            Toast.makeText(applicationContext, "카카오톡이 설치되어있지 않습니다.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun isServiceMember() {
        // TODO : Server 카카오회원번호 얻어와서 서비스에 회원가입 되어있는(우리 DB에 있는) 유저인지 확인
        if(checkMemberInfo()) { //서비스 가입된 유저일 경우
            // 로그인 됨, 메인화면으로 이동
            //TODO : 타이밍 안맞아서 데이터 안넘어감. how?
            Log.d("login - doKakaoLogin", "닉네임 : ${userNickname}")
            val mainIntent = Intent(this, MainActivity::class.java)
            mainIntent.putExtra("nickname", userNickname)
            startActivity(mainIntent)
            finish()
        }
        else{ //서비스 미가입 유저
            // Toast.makeText(this, "${userNickname}님은 가입되지 않은 사용자입니다. 회원가입이 필요합니다.", Toast.LENGTH_SHORT).show()

            // TODO : 회원가입 다이얼로그 -> 회원가입 하겠다고 하면 동네설정 화면으로 이동.
            //리스너로 넣을 함수를 만들어 변수로 선언
            val eventHandler = object : DialogInterface.OnClickListener {
                override fun onClick(p0: DialogInterface?, p1: Int) { //p1에 눌린 버튼의 값이 들어옴!
                    if(p1==DialogInterface.BUTTON_POSITIVE){
                        //회원가입 OK 시 SearchAddress 화면으로 이동해서 결과 받아옴
                        val addressIntent = Intent(applicationContext, SearchAddressActivity::class.java)
                        resultLauncher.launch(addressIntent)
                    }
                    else if(p1==DialogInterface.BUTTON_NEGATIVE){
                        Toast.makeText(applicationContext, "회원가입을 하지 않습니다.", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            AlertDialog.Builder(this).run {
                setTitle("회원가입이 필요합니다.")
                //setIcon(android.R.drawable.ic_dialog_info) //아이콘도 설정 가능
                setMessage("OK 버튼을 누르면 회원가입 화면으로 이동합니다.")
                setPositiveButton("OK", eventHandler) //버튼을 추가하고 이 버튼이 눌렸을때의 리스너를 넣음
                setNegativeButton("Cancel", eventHandler)
                show()
            }
        }

    }

    private fun postSignUp(userid: Long, address: ArrayList<String>) {
        //TODO : Server : 회원가입 요청 후 성공 시
        Toast.makeText(applicationContext, "회원가입이 완료되었습니다.로그인해주세요.", Toast.LENGTH_SHORT).show()
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
                //Toast.makeText(this, "${userNickname}님 카카오 계정 정보 가져오기 성공.", Toast.LENGTH_SHORT).show()
            }
        }
        return false
    }
}