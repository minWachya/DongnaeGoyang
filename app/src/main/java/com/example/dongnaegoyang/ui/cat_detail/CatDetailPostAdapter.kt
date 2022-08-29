package com.example.dongnaegoyang.ui.cat_detail

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dongnaegoyang.R
import com.example.dongnaegoyang.custom.CustomDialog
import com.example.dongnaegoyang.custom.CustomToast.showCustomToast
import com.google.android.material.bottomsheet.BottomSheetDialog

// 게시글 테이터 클래스(닉네임, 게시 시간, 내용)
data class CatPost(val nickname: String, val time: String, val content: String)

// 고양이 상세 페이지: '오늘 기록' 탭 게시글 어댑터
class CatDetailPostAdapter(val fragmentManager: FragmentManager) : RecyclerView.Adapter<CatDetailPostAdapter.ViewHolder>() {
    var posts = ArrayList<CatPost>()  // 게시글 배열
    lateinit var dialog: CustomDialog   // 삭제 확인 디이얼로그

    // 뷰홀더 생성
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatDetailPostAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_cat_detail_post, parent, false)
        return ViewHolder(itemView)
    }

    // position 번째 아이템 설정하기
    override fun onBindViewHolder(holder: CatDetailPostAdapter.ViewHolder, position: Int) {
        val post = posts[position]
        holder.setItem(post)
    }

    // 아이템 갯수 리턴
    override fun getItemCount() = posts.size

    // 게시글 로드
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setItem(post: CatPost) {
            // 게시글 정보
            itemView.findViewById<TextView>(R.id.itemTvNickname).text = post.nickname
            itemView.findViewById<TextView>(R.id.itemTvTime).text = post.time
            itemView.findViewById<TextView>(R.id.itemTvContent).text = post.content
            // 상세 메뉴 달기
            setBottomSheetDialog(itemView, this)
        }
    }

    // position 위치의 글 삭제 후 어댑터 갱신
    private fun removeData(position: Int) {
        posts.removeAt(position)
        notifyItemRemoved(position)
    }

    // 상세 메뉴 설정
    private fun setBottomSheetDialog(itemView: View, viewHolder: ViewHolder) {
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
            removeData(position)
            dialog.dismiss()
            Toast(itemView.context).showCustomToast ("기록이 삭제되었습니다.", itemView.context)
        }
        dialog = CustomDialog("기록 삭제", "기록을 완전히 삭제할까요?",
            null, deleteListener)
        dialog.show(fragmentManager, "CustomDialog")
    }

}