package com.example.dongnaegoyang.ui.cat_detail

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.widget.Toast
import com.example.dongnaegoyang.R
import com.example.dongnaegoyang.custom.CustomToast.showCustomToast
import com.example.dongnaegoyang.databinding.FragmentCatDetailPostBinding
import com.example.dongnaegoyang.ui.base.BaseFragment

private const val TAG = "mmmCatDetailPostFragment"

// 고양이 상세 페이지: '오늘 기록' 탭
class CatDetailPostFragment : BaseFragment<FragmentCatDetailPostBinding>(R.layout.fragment_cat_detail_post) {
    lateinit var postAdapter: CatDetailPostAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner

        // 게시글 가져오기
        getPosts()
        // EditText 입력 시 스크롤 하면 EditText 내용 스크롤되게
        setEditTextScroll()
        // '게시' 버튼 클릭: 서버에 게시글 데이터 전달
        postPost()
    }

    private fun getPosts() {
        postAdapter = CatDetailPostAdapter(parentFragmentManager)
        binding.rcPost.adapter = postAdapter
        postAdapter.posts.add(CatPost("서울우유좋아", "15분 전", "아침 일찍 일어나 우유 주고 왔습니다. 아 물론 고양이 우유입니다."))
        postAdapter.posts.add(CatPost("고양이재채기", "14분 전", "츄르 주려고 러닝 나갔는데 다른 분이 주시더군요(아쉽)"))
        postAdapter.posts.add(CatPost("테스트1", "13분 전", "테스트입니다."))
        postAdapter.posts.add(CatPost("테스트2", "12분 전", "테스트입니다."))
        postAdapter.posts.add(CatPost("테스트3", "11분 전", "테스트입니다."))
        postAdapter.posts.add(CatPost("테스트4", "10분 전", "테스트입니다."))
        postAdapter.notifyDataSetChanged()
    }

    private fun postPost() {
        binding.btnPost.setOnClickListener {
            // 내용 없을 시 반환
            if (binding.editPost.text.isEmpty()) {
                Toast(context).showCustomToast ("내용을 입력해주새요.", requireContext())
                return@setOnClickListener
            }
            // 게시글 생성
            createPost()
        }
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
    }

    // EditText Scroll 설정
    private fun setEditTextScroll() {
        binding.editPost.setOnTouchListener(OnTouchListener { v, event ->
            if (v.id == binding.editPost.id) {
                v.parent.requestDisallowInterceptTouchEvent(true)
                when (event.action and MotionEvent.ACTION_MASK) {
                    MotionEvent.ACTION_UP -> v.parent.requestDisallowInterceptTouchEvent(false)
                }
            }
            false
        })
    }
}