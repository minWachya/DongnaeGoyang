package com.example.dongnaegoyang.cat_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.dongnaegoyang.R
import com.example.dongnaegoyang.databinding.FragmentCatDetailPostBinding

private const val TAG = "mmmCatDetailPostFragment"
private var _binding: FragmentCatDetailPostBinding? = null
private val binding get() = _binding!!

// 고양이 상세 페이지: '오늘 기록' 탭
class CatDetailPostFragment : Fragment() {
    lateinit var postAdapter: CatDetailPostAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCatDetailPostBinding.inflate(inflater, container, false)
        val view = binding.root

        // 게시글 가져오기
        postAdapter = CatDetailPostAdapter()
        binding.rcPost.adapter = postAdapter
        postAdapter.posts.add(CatPost("서울우유좋아", "15분 전", "아침 일찍 일어나 우유 주고 왔습니다. 아 물론 고양이 우유입니다."))
        postAdapter.posts.add(CatPost("고양이재채기", "14분 전", "츄르 주려고 러닝 나갔는데 다른 분이 주시더군요(아쉽)"))
        postAdapter.posts.add(CatPost("테스트1", "13분 전", "테스트입니다."))
        postAdapter.posts.add(CatPost("테스트2", "12분 전", "테스트입니다."))
        postAdapter.posts.add(CatPost("테스트3", "11분 전", "테스트입니다."))
        postAdapter.posts.add(CatPost("테스트4", "10분 전", "테스트입니다."))
        postAdapter.notifyDataSetChanged()

        // '게시' 버튼 클릭: 서버에 게시글 데이터 전달
        binding.btnPost.setOnClickListener {
            // 내용 없을 시 반환
            if (binding.editPost.text.isEmpty()) {
                Toast.makeText(context, "내용을 입력해주새요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            // 게시글 생성
            createPost()
        }

        return view
    }

    // 게시글 생성
    private fun createPost() {
        val nickname = "서버 아직 없음"
        val time = "nn분 전" // System.currentTimeMillis().toString()
        val content = binding.editPost.text.toString()
        val post = CatPost(nickname, time, content)
        // editText 내용 비우기
        binding.editPost.text = null
        // 리사이클러뷰에 저장+새로고침
        postAdapter.posts.add(post)
        postAdapter.notifyDataSetChanged()

        Toast.makeText(context, "게시글이 등록되었습니다.", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}