package com.example.dongnaegoyang.cat_detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dongnaegoyang.R

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
            itemView.findViewById<TextView>(R.id.itemTvNickname).text = post.nickname
            itemView.findViewById<TextView>(R.id.itemTvTime).text = post.time
            itemView.findViewById<TextView>(R.id.itemTvContent).text = post.content
        }
    }
}