package com.example.dongnaegoyang.sidebar

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.dongnaegoyang.custom.CustomToast.showCustomToast
import com.example.dongnaegoyang.databinding.FragmentSettingBinding
import com.example.dongnaegoyang.home.MainActivity
import com.kakao.sdk.user.UserApiClient

private const val TAG = "mmmSettingFragment"

private var _binding: FragmentSettingBinding? = null
private val binding get() = _binding!!

class SettingFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSettingBinding.inflate(inflater, container, false)
        val view = binding.root

        // 로그아웃하기
        binding.btnLogout.setOnClickListener {
            UserApiClient.instance.logout { error ->
                if (error != null) {
                    Toast(context).showCustomToast ("로그아웃 실패 $error", view.context)
                }else {
                    Toast(context).showCustomToast ("로그아웃 되었습니다", view.context)
                }
                val intent = Intent(activity, MainActivity::class.java)
                startActivity(intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP))
            }
        }

        // 로그아웃 밑 유저 이메일
        UserApiClient.instance.me { user, error ->
            binding.tvNickname.text = "${user?.kakaoAccount?.email}"
            // 이름 출력 ${user.kakaoAccount?.profile?.nickname}
        }

        // 탈퇴하기
        binding.btnSecession.setOnClickListener {
            UserApiClient.instance.unlink { error ->
                if (error != null) {
                    Toast(context).showCustomToast ("탈퇴 실패 $error", view.context)
                }else {
                    Toast(context).showCustomToast ("탈퇴 되었습니다", view.context)
                    val intent = Intent(activity, MainActivity::class.java)
                    startActivity(intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP))
                }
            }
        }

        return view
    }

    companion object {
        const val TAG = "SettingFragment"
    }
}