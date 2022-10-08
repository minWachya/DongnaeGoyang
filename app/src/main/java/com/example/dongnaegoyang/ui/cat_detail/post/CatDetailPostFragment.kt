package com.example.dongnaegoyang.ui.cat_detail.post

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.dongnaegoyang.R
import com.example.dongnaegoyang.custom.CustomToast.showCustomToast
import com.example.dongnaegoyang.data.remote.model.response.PhotoList
import com.example.dongnaegoyang.data.remote.model.response.Post
import com.example.dongnaegoyang.databinding.FragmentCatDetailPostBinding
import com.example.dongnaegoyang.ui.base.BaseFragment
import com.example.dongnaegoyang.ui.cat_detail.info.CatDetailPhotoAdapter
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
        setObserverCatDetailInfo()
    }

    private fun getCatDetailPost(catIdx: Long, page: Int) {
        viewModel.getCatDetailPost(catIdx, page)
    }

    private fun setObserverCatDetailInfo() {
        viewModel.catDetailPostResponse.observe(viewLifecycleOwner) {
            binding.executePendingBindings()
            setPostAdapter(it.data.postList)
        }
    }

    private fun setPostAdapter(postList: List<Post>) {
        binding.rcPost.adapter = CatDetailPostAdapter(parentFragmentManager).apply {
            submitList(postList)
        }
    }

//    private fun postPost() {
//        binding.btnPost.setOnClickListener {
//            // 내용 없을 시 반환
//            if (binding.editPost.text.isEmpty()) {
//                Toast(context).showCustomToast ("내용을 입력해주새요.", requireContext())
//                return@setOnClickListener
//            }
//            // 게시글 생성
//            createPost()
//        }
//    }

//    // 게시글 생성
//    private fun createPost() {
//        val nickname = "서버 아직 없음"
//        val time = "nn분 전" // System.currentTimeMillis().toString()
//        val content = binding.editPost.text.toString()
//        val post = CatPost(nickname, time, content)
//        // editText 내용 비우기
//        binding.editPost.text = null
//        // 리사이클러뷰에 저장+새로고침
//        postAdapter.posts.add(post)
//        postAdapter.notifyDataSetChanged()
//    }

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