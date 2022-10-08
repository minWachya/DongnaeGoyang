package com.example.dongnaegoyang.ui.cat_detail.post

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.dongnaegoyang.R
import com.example.dongnaegoyang.custom.CustomToast.showCustomToast
import com.example.dongnaegoyang.data.remote.model.response.Post
import com.example.dongnaegoyang.databinding.FragmentCatDetailPostBinding
import com.example.dongnaegoyang.login.kakaoLogin.SharedPreferenceController
import com.example.dongnaegoyang.ui.base.BaseFragment
import com.example.dongnaegoyang.ui.common.EventObserver
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "mmmCatDetailPostFragment"

// 고양이 상세 페이지: '오늘 기록' 탭
@AndroidEntryPoint
class CatDetailPostFragment(val catIdx: Long) : BaseFragment<FragmentCatDetailPostBinding>(R.layout.fragment_cat_detail_post) {
    private val viewModel: CatDetailPostViewModel by viewModels()
    private var page = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner

        // EditText 입력 시 스크롤 하면 EditText 내용 스크롤되게
        setEditTextScroll()

        getCatDetailPost(catIdx, page)
        setPostButtonClickListener()
        setObserverCatDetailInfo()
        setObserverCreatePost()
    }

    private fun getCatDetailPost(catIdx: Long, page: Int) {
        viewModel.getCatDetailPost(catIdx, page)
    }

    private fun setObserverCatDetailInfo() {
        viewModel.catDetailPostResponse.observe(viewLifecycleOwner, EventObserver {
            binding.executePendingBindings()
            setPostAdapter(it.data.postList)
        })
    }

    private fun setPostAdapter(postList: List<Post>) {
        binding.rcPost.adapter = CatDetailPostAdapter(parentFragmentManager).apply {
            submitList(postList)
        }
    }

    private fun setObserverCreatePost() {
        viewModel.createPostResponse.observe(viewLifecycleOwner) {
            Toast.makeText(context, "게시글 등록 성공!", Toast.LENGTH_SHORT).show()
            // 새로고침
            page = 0
            getCatDetailPost(catIdx, page)
        }
    }

    private fun setPostButtonClickListener() {
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
        val token = SharedPreferenceController.getToken(requireContext())
        val content = binding.editPost.text.toString()
        viewModel.postCatPost(catIdx, token, content)
        // editText 내용 비우기
        binding.editPost.text = null
    }

    // EditText Scroll 설정
    private fun setEditTextScroll() {
        binding.editPost.setOnTouchListener { v, event ->
            if (v.id == binding.editPost.id) {
                v.parent.requestDisallowInterceptTouchEvent(true)
                when (event.action and MotionEvent.ACTION_MASK) {
                    MotionEvent.ACTION_UP -> v.parent.requestDisallowInterceptTouchEvent(false)
                }
            }
            false
        }
    }
}