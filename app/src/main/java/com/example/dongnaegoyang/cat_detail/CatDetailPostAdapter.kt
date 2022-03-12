package com.example.dongnaegoyang.cat_detail

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.dongnaegoyang.R
import com.google.android.material.bottomsheet.BottomSheetDialog

// 게시글 테이터 클래스(닉네임, 게시 시간, 내용)
data class CatPost(val nickname: String, val time: String, val content: String)

// 고양이 상세 페이지: '오늘 기록' 탭 게시글 어댑터
class CatDetailPostAdapter : RecyclerView.Adapter<CatDetailPostAdapter.ViewHolder>() {
    var posts = ArrayList<CatPost>()  // 게시글 배열

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
            // 서브 메뉴
            val inflater: LayoutInflater = itemView.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val bottomSheetView = inflater.inflate(R.layout.custom_dialog_cat_detail_post_more, null)
            val bottomSheetDialog = BottomSheetDialog(itemView.context, R.style.DialogCustomTheme) // dialog에 sytle 추가
            bottomSheetDialog.setContentView(bottomSheetView)
            // 삭제 버튼
            val btnDelete = bottomSheetView.findViewById<Button>(R.id.btnDelete)
            btnDelete.setOnClickListener {
                removeData(this.layoutPosition)
                Toast.makeText(itemView.context, "삭제하였습니다.", Toast.LENGTH_SHORT).show()
                bottomSheetDialog.dismiss()
            }
            // 취소 버튼
            val btnCancel = bottomSheetView.findViewById<Button>(R.id.btnCancel)
            btnCancel.setOnClickListener { bottomSheetDialog.dismiss() }
            itemView.findViewById<ImageView>(R.id.itemImgSubMenu).setOnClickListener {
                bottomSheetDialog.show()
            }
        }
    }

    // position 위치의 글 삭제 후 어댑터 갱신
    fun removeData(position: Int) {
        posts.removeAt(position)
        notifyItemRemoved(position)
    }
}