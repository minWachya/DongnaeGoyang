package com.example.dongnaegoyang.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import com.example.dongnaegoyang.R
import com.example.dongnaegoyang.address_search.SearchAddressActivity
import com.example.dongnaegoyang.api.ApiClient
import com.example.dongnaegoyang.api.AuthClient
import com.example.dongnaegoyang.databinding.ActivityLoginBinding
import com.example.dongnaegoyang.databinding.CustomDialogBinding
import com.example.dongnaegoyang.login.kakaoLogin.SharedPreferenceController
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.kakao.sdk.user.UserApiClient
import retrofit2.Call
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var resultLauncher : ActivityResultLauncher<Intent>
    private var userId = -1L
    private var userNickname = "No Value"
//    private var address = arrayListOf<String>("No Value", "No Value", "No Value")
    private var address = HashMap<String, String>()
    private lateinit var notMemberDialog: android.app.AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        address["si"] = "No Value"
        address["gu"] = "No Value"
        address["dong"] = "No Value"


        binding.btnLoginFinish.setOnClickListener {
            finish()
        }

        binding.btnKakaoLogin.setOnClickListener {
            doKakaoLogin("login")
        }

        //회원가입 눌렀을 때
        val signUpView = layoutInflater.inflate(R.layout.activity_sign_up_dialog, null)
        val signUpBottomDialog = BottomSheetDialog(this, R.style.DialogCustomTheme)
        signUpBottomDialog.setContentView(signUpView)
        binding.tvLoginSignUp.setOnClickListener {
            signUpBottomDialog.show()
        }
        //카카오로 시작하기 눌렀을 때
        val btnKakaoStart = signUpView.findViewById<ImageView>(R.id.btnKakaoStart)
        btnKakaoStart.setOnClickListener {
            Log.d("jh sign up", "카카오로 시작하기 버튼 눌림")
            doSignUp()
            signUpBottomDialog.dismiss()
        }


        //다이얼로그 뷰 구성해둠(로그인 실패 시 notMemberDialog.show()로 사용)
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

        //회원가입 주소받기 launcher - onCreate나 onStart에서 초기화해 주어야 함
        resultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) { result ->
//            Log.d("login address", "launcher 실행")
            if (result.resultCode == Activity.RESULT_OK) {
                address["si"] = result.data?.getStringExtra("si") ?: "Get No Value"
                address["gu"] = result.data?.getStringExtra("gu") ?: "Get No Value"
                address["dong"] = result.data?.getStringExtra("dong") ?: "Get No Value"
                Log.d("jh login address", "주소 : ${address["si"]}, ${address["gu"]}, ${address["dong"]}")
                doKakaoLogin("signUp")
//                Toast.makeText(applicationContext, "회원가입 요청 중입니다.", Toast.LENGTH_SHORT).show()

            }
            else{
                Log.d("jh login address", "launcher RESULT OK 아님")
                Log.d("jh login address", "launcher RESULT : ${result.resultCode}")

            }
        }

    }

    //회원가입을 위해 주소를 입력받고, 돌아왔을 때 resultLauncher에서 카카오로그인을 실행하는 함수
    private fun doSignUp() {
        //launcher 초기화는 onCreate에서 했음
        val addressIntent = Intent(applicationContext, SearchAddressActivity::class.java)
        addressIntent.putExtra("from", "signUp")
        resultLauncher.launch(addressIntent)
    }

    //SP에 저장된 토큰이 없을 때 로그인 진행해 액세스 토큰 받아옴
    private fun doKakaoLogin(type: String) {
        Log.d("jh kakao login", "doKakaoLogin 시작")
        // 카카오톡 설치 여부 확인 isKakaoTalkLoginAvailable
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
            // 카카오톡으로 로그인
            UserApiClient.instance.loginWithKakaoTalk(this) { token, error ->
                if (error != null) {
                    Log.e("jh", "로그인 실패", error)
                }
                else if (token != null) {
                    // 카카오 로그인(토큰 발급) 성공, 저장
                    Log.i("jh", "카카오 토큰 발급 성공 token: ${token.accessToken}")

                    if(type == "login"){
                        callPostLogin(token.accessToken)
                    }
                    else if(type == "signUp"){
                        callPostSignUp(token.accessToken, address)
                    }
                }
            }
        } else {
            Log.e("jh", "카카오톡이 설치되어있지 않습니다.")
            Toast.makeText(this, "카카오톡이 설치되어있지 않습니다.", Toast.LENGTH_SHORT).show()
        }

    }

    private val authService = AuthClient.authService
    private fun callPostLogin(kakaoToken: String) { //body: HashMap<String, String> 없음
        val responseData = MutableLiveData<ModelLoginResponse>()

        authService.postLoginKakao(kakaoToken)
            .enqueue(object : retrofit2.Callback<ModelLoginResponse> {
                override fun onResponse(
                    call: Call<ModelLoginResponse>,
                    response: Response<ModelLoginResponse>
                ) {
                    val status = response.code()
                    Log.d("jh", "status : ${status}")

                    if(response.isSuccessful){
                        responseData.value = response.body()
                        val data = responseData.value?.data
                        Log.d("jh", "${status}. data : ${data}")

                        if(data == null){
                            Log.d("jh", "${status} but data is null ")
                        }else{
                            Log.d("jh", "login data save start")
                            SharedPreferenceController.saveToken(applicationContext, data.token)
                            SharedPreferenceController.saveSido(applicationContext, data.sido)
                            SharedPreferenceController.saveGugun(applicationContext, data.gugun)
                            SharedPreferenceController.saveNickname(applicationContext, data.nickname)
                            SharedPreferenceController.setModeLogin(applicationContext)
                            val nickname = SharedPreferenceController.getNickname(applicationContext)
                            Log.d("jh", "SharedPreferenceController check" +
                                    "token : ${SharedPreferenceController.getToken(applicationContext)}" +
                                    "sido : ${SharedPreferenceController.getSido(applicationContext)}" +
                                    "gugun : ${SharedPreferenceController.getGugun(applicationContext)}" +
                                    "mode : ${SharedPreferenceController.getMode(applicationContext)}" +
                                    "nickname : ${nickname}")
                            Toast.makeText(applicationContext, "${nickname}님, 로그인 되었습니다.", Toast.LENGTH_SHORT).show()
                            finish() //로그인 화면 종료, 홈화면으로 돌아감
                        }
                    }
                    else{ //response.isSuccessful == false 즉 status가 200~300 이 아닌 응답코드일 때
                        Log.d("jh", "response.errorBody : ${response.errorBody()?.string()}")
                        when (status){
                            401 -> { //회원가입 필요
                                notMemberDialog.show()
                            }
                            409 -> { //카카오 토큰 만료 -> 이럴 일 없음. 토큰 바로 발급해서 쓰니까
                                Toast.makeText(applicationContext, "카카오 토큰이 만료되었습니다.", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }

                }

                override fun onFailure(call: Call<ModelLoginResponse>, t: Throwable) {
                    Log.e("jh login onFailure", "실패")
                    t.printStackTrace()
                }
            })
    }

    private fun callPostSignUp(kakaoToken: String, body: HashMap<String, String>) {
        val responseData = MutableLiveData<ModelSignUpResponse>()

        authService.postSignUp(kakaoToken, body)
            .enqueue(object : retrofit2.Callback<ModelSignUpResponse> {
                override fun onResponse(
                    call: Call<ModelSignUpResponse>,
                    response: Response<ModelSignUpResponse>
                ) {
                    val status = response.code()
                    Log.d("jh", "status : ${status}")

                    if(response.isSuccessful) {
                        responseData.value = response.body()
                        val data = responseData.value?.data
                        Log.d("jh", "${status}. data : ${data}")
                        Toast.makeText(applicationContext, "가입 성공! 로그인해주세요.", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        Log.d("jh", "response.errorBody : ${response.errorBody()?.string()}")
                        when(status){
                            409 -> { //이미 회원임
                                Toast.makeText(applicationContext, "이미 가입된 회원입니다. 로그인해주세요.", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }


                }

                override fun onFailure(call: Call<ModelSignUpResponse>, t: Throwable) {
                    Log.e("jh signUp onFailure", "실패")
                    t.printStackTrace()
                }
            })
    }
}