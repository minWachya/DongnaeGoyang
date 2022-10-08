package com.example.dongnaegoyang.ui.cat_detail.post

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dongnaegoyang.R
import com.example.dongnaegoyang.custom.CustomDialog
import com.example.dongnaegoyang.custom.CustomToast.showCustomToast
import com.example.dongnaegoyang.data.remote.model.response.Post
import com.example.dongnaegoyang.databinding.ItemCatDetailPostBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

// 고양이 상세 페이지: '오늘 기록' 탭 게시글 어댑터
class CatDetailPostAdapter(private val fragmentManager: FragmentManager)  :
    ListAdapter<Post, CatDetailPostAdapter.PostViewHolder>(
        PostListDiffCallback()
    ) {
    private lateinit var binding: ItemCatDetailPostBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        binding = ItemCatDetailPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class PostViewHolder(private val binding: ItemCatDetailPostBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(post: Post) {
            binding.post = post
        }
    }

//    // position 위치의 글 삭제 후 어댑터 갱신
//    private fun removeData(position: Int) {
//        posts.removeAt(position)
//        notifyItemRemoved(position)
//    }

    // 상세 메뉴 설정
    private fun setBottomSheetDialog(itemView: View, viewHolder: PostViewHolder) {
        val inflater: LayoutInflater = itemView.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val bottomSheetView = inflater.inflate(R.layout.custom_dialog_cat_detail_post_more, null)
        val bottomSheetDialog = BottomSheetDialog(itemView.context, R.style.DialogCustomTheme) // dialog에 sytle 추가
        bottomSheetDialog.setContentView(bottomSheetView)
        // 삭제 버튼
        val btnDelete = bottomSheetView.findViewById<Button>(R.id.btnDelete)
        btnDelete.setOnClickListener {
            // 삭제 확인 다이얼로그 띄우기
            showDeleteCheckDialog(viewHolder.layoutPosition, itemView)
            bottomSheetDialog.dismiss()
        }
        // 취소 버튼
        val btnCancel = bottomSheetView.findViewById<Button>(R.id.btnCancel)
        btnCancel.setOnClickListener { bottomSheetDialog.dismiss() }
        itemView.findViewById<ImageView>(R.id.itemImgSubMenu).setOnClickListener {
            bottomSheetDialog.show()
        }
    }

    // 글 삭제 확인 다이얼로그
    private fun showDeleteCheckDialog(position: Int, itemView: View) {
        // 삭제 버튼 클릭 시 해당 글 삭제
        val deleteListener: View.OnClickListener = View.OnClickListener {
//            removeData(position)
            Toast(itemView.context).showCustomToast ("기록이 삭제되었습니다.", itemView.context)
        }
        val dialog = CustomDialog("기록 삭제", "기록을 완전히 삭제할까요?",
            null, deleteListener)
        dialog.show(fragmentManager, "CustomDialog")
    }

    class PostListDiffCallback : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean =
            oldItem.postIdx == newItem.postIdx
        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean =
            oldItem == newItem
    }
}