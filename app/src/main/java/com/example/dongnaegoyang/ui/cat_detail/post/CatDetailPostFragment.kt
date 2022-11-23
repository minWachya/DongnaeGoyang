package com.example.dongnaegoyang.ui.cat_detail.post

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.dongnaegoyang.R
import com.example.dongnaegoyang.common.KEY_CAT_IDX
import com.example.dongnaegoyang.custom.CustomDialog
import com.example.dongnaegoyang.custom.CustomToast.showCustomToast
import com.example.dongnaegoyang.databinding.FragmentCatDetailPostBinding
import com.example.dongnaegoyang.login.kakaoLogin.SharedPreferenceController
import com.example.dongnaegoyang.ui.base.BaseFragment
import com.example.dongnaegoyang.ui.common.EventObserver
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "mmmCatDetailPostFragment"

// 고양이 상세 페이지: '오늘 기록' 탭
@AndroidEntryPoint
class CatDetailPostFragment : BaseFragment<FragmentCatDetailPostBinding>(R.layout.fragment_cat_detail_post) {
    private val viewModel: CatDetailPostViewModel by viewModels()

    lateinit var dialog: CustomDialog

    var catIdx: Long = 6L

    lateinit var adapter: PostAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner

        // EditText 입력 시 스크롤 하면 EditText 내용 스크롤되게
        setEditTextScroll()

        catIdx = arguments?.getLong(KEY_CAT_IDX) ?: 6L

        initPagingPostAdapter()
        initPagingPost(catIdx)
        setPostButtonClickListener()
        setObserverCreatePost()
        setObserverPostMenu()
        setObserverDeletePost()
    }

    private fun initPagingPostAdapter() {
        adapter = PostAdapter(viewModel)
        binding.rvPost.adapter = adapter
    }

    private fun initPagingPost(catIdx: Long) {
        lifecycleScope.launchWhenStarted {
            viewModel.getPagingPost(catIdx).collect {
                adapter.submitData(it)
            }
        }
    }

    private fun setObserverCreatePost() {
        viewModel.createPostResponse.observe(viewLifecycleOwner) {
            Toast(context).showCustomToast ("게시글이 등록되었습니다.", requireContext())
            refresh()
        }
    }

    private fun setPostButtonClickListener() {
        binding.btnPost.setOnClickListener {
            // 내용 없을 시 반환
            if (binding.editPost.text.isEmpty())
                Toast(context).showCustomToast ("내용을 입력해주새요.", requireContext())
            // 게시글 생성
            else createPost()
        }
    }
    private fun createPost() {
        val token = SharedPreferenceController.getToken(requireContext())
        val content = binding.editPost.text.toString()
        viewModel.postCatPost(catIdx, token, content)
        // editText 내용 비우기
        binding.editPost.text = null
    }

    private fun setObserverPostMenu() {
        viewModel.openPostEvent.observe(viewLifecycleOwner, EventObserver {
            showBottomSheetDialog(it)
        })
    }

    // 상세 메뉴 설정
    private fun showBottomSheetDialog(postIdx: Long) {
        val inflater: LayoutInflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val bottomSheetView = inflater.inflate(R.layout.custom_dialog_cat_detail_post_more, null)
        val bottomSheetDialog = BottomSheetDialog(context!!, R.style.DialogCustomTheme) // dialog에 sytle 추가
        bottomSheetDialog.setContentView(bottomSheetView)
        // 삭제 버튼
        val btnDelete = bottomSheetView.findViewById<Button>(R.id.btnDelete)
        btnDelete.setOnClickListener {
            // 삭제 확인 다이얼로그 띄우기
            showDeleteCheckDialog(postIdx)
            bottomSheetDialog.dismiss()
        }
        // 취소 버튼
        val btnCancel = bottomSheetView.findViewById<Button>(R.id.btnCancel)
        btnCancel.setOnClickListener { bottomSheetDialog.dismiss() }
        bottomSheetDialog.show()
    }
    // 글 삭제 확인 다이얼로그
    private fun showDeleteCheckDialog(postIdx: Long) {
        // 삭제 버튼 클릭 시 해당 글 삭제
        dialog = CustomDialog("기록 삭제", "기록을 완전히 삭제할까요?", null) {
            deletePost(postIdx)
            dialog.dismiss()
        }
        dialog.show(parentFragmentManager, "CustomDialog")
    }

    private fun deletePost(postIdx: Long) {
        val token = SharedPreferenceController.getToken(requireContext())
        viewModel.deleteCatPost(postIdx, token)
    }

    private fun setObserverDeletePost() {
        viewModel.deletePostResponse.observe(viewLifecycleOwner) {
            Toast(context).showCustomToast ("기록이 삭제되었습니다.", requireContext())
            refresh()
        }
    }

    private fun refresh() {
        adapter.refresh()
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