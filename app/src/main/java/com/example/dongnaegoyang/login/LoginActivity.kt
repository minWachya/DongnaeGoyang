package com.example.dongnaegoyang.login

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.dongnaegoyang.R
import com.example.dongnaegoyang.address_search.SearchAddressActivity
import com.example.dongnaegoyang.databinding.ActivityLoginBinding
import com.example.dongnaegoyang.databinding.ActivitySignUpDialogBinding
import com.example.dongnaegoyang.databinding.CustomDialogBinding
import com.example.dongnaegoyang.home.MainActivity
import com.example.dongnaegoyang.login.kakaoLogin.KakaoLogin
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.kakao.sdk.auth.AuthApiClient
import com.kakao.sdk.common.model.KakaoSdkError
import com.kakao.sdk.user.UserApiClient

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var resultLauncher : ActivityResultLauncher<Intent>
    private var userId = -1L
    private var userNickname = "No Value"
    private var address = arrayListOf<String>("No Value", "No Value", "No Value")
    private lateinit var notMemberDialog: android.app.AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        binding.btnLoginFinish.setOnClickListener {
            finish()
        }

        binding.btnKakaoLogin.setOnClickListener {
            if(KakaoLogin(this).checkKakaoToken()){ // 로그인 됨, 메인화면으로 이동
                //TODO : 타이밍 안맞아서 데이터 안넘어감. how?
                Log.d("login - doKakaoLogin", "닉네임 : ${userNickname}")
                val mainIntent = Intent(this, MainActivity::class.java)  //TODO : 원래 화면에서
                mainIntent.putExtra("nickname", userNickname)
                startActivity(mainIntent)
                finish()
            }
            else{ //로그인 실패. 회원아님.
                notMemberDialog.show() //TODO : 원래 화면에서
            }
        }

        val signUpView = layoutInflater.inflate(R.layout.activity_sign_up_dialog, null)
        val signUpBottomDialog = BottomSheetDialog(this, R.style.DialogCustomTheme)
        signUpBottomDialog.setContentView(signUpView)
        binding.tvLoginSignUp.setOnClickListener {
            signUpBottomDialog.show()
        }
        val btnKakaoStart = signUpView.findViewById<ImageView>(R.id.btnKakaoStart)
        btnKakaoStart.setOnClickListener {
            Log.d("sign up", "카카오로 시작하기 버튼 눌림")
            KakaoLogin(this).doKakaoLogin() //TODO : 회원 정보를 잔환하던지 회원가입 루트를 따로 구성할 것
            doSignUp()
            signUpBottomDialog.dismiss()
        }
/* //바인딩 안됨??
        val signUpBinding = ActivitySignUpDialogBinding.inflate(layoutInflater)
        signUpBinding.btnKakaoStart.setOnClickListener {
            Log.d("sign up", "카카오로 시작하기 버튼 눌림")
            KakaoLogin(parent).doKakaoLogin()
        }
*/


        //다이얼로그 뷰 구성해둠
        val dialogBinding = CustomDialogBinding.inflate(layoutInflater) //커스텀 다이얼로그로 쓸 뷰 가져옴
        notMemberDialog = android.app.AlertDialog.Builder(this)
            .setView(dialogBinding.root)
            .create()

        dialogBinding.customTvTitle.text = "회원가입이 필요합니다."
        dialogBinding.customTvContent.text = "OK버튼을 누르면 회원가입 화면으로 이동합니다."
        dialogBinding.customTvBtn1.text = "Cancel"
        dialogBinding.customTvBtn2.text = "OK"

        dialogBinding.customTvBtn1.setOnClickListener {
            notMemberDialog.dismiss()
        }
        dialogBinding.customTvBtn2.setOnClickListener {
            //회원가입 OK 시 SearchAddress 화면으로 이동해서 결과 받아옴
            doSignUp()
            notMemberDialog.dismiss()
        }

    }

    private fun doSignUp() {
        //회원가입 주소받기
        resultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) { result ->
//            Log.d("login address", "launcher 실행")
            if (result.resultCode == Activity.RESULT_OK) {
                address[0] = result.data?.getStringExtra("si") ?: "Get No Value"
                address[1] = result.data?.getStringExtra("gu") ?: "Get No Value"
                address[2] = result.data?.getStringExtra("dong") ?: "Get No Value"
                Log.d("login address", "주소 : ${address[0]}, ${address[1]}, ${address[2]}")
                Toast.makeText(applicationContext, "회원가입 요청 중입니다.", Toast.LENGTH_SHORT).show()

                //TODO : 지역변수 말고 SharedPreferences에 저장된 값 가져올 것
                postSignUp(userId, address)
            }
            else{
                Log.d("login address", "launcher RESULT OK 아님")
                Log.d("login address", "launcher RESULT : ${result.resultCode}")

            }
        }

        val addressIntent = Intent(applicationContext, SearchAddressActivity::class.java)
        addressIntent.putExtra("from", "signUp")
        resultLauncher.launch(addressIntent)

    }

    private fun postSignUp(userid: Long, address: ArrayList<String>) {
        //TODO : Server : 회원가입 요청 후 성공 시
        Toast.makeText(applicationContext, "회원가입이 완료되었습니다.로그인해주세요.", Toast.LENGTH_SHORT).show()
    }


}