package com.example.dongnaegoyang.cat_detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.dongnaegoyang.R

// 고양이 상세 페에지: '정보' 탭 사진 어댑터
class CatDetailPhotoAdapter : RecyclerView.Adapter<CatDetailPhotoAdapter.ViewHolder>() {
    var urls = ArrayList<String>()  // Url 배열

    // 뷰홀더 생성
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatDetailPhotoAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_cat_detail_photo, parent, false)

        return ViewHolder(itemView).apply {
            itemView.setOnClickListener {

            }
        }
    }

    // position 번째 아이템 설정하기
    override fun onBindViewHolder(holder: CatDetailPhotoAdapter.ViewHolder, position: Int) {
        val url = urls[position]
        holder.setItem(url)
    }

    // 아이템 갯수 리턴
    override fun getItemCount() = urls.size

    // 사진 로드하기
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setItem(url: String) {
            Glide.with(itemView)
                .load(url)
                .error(R.drawable.ic_launcher_background)                  // 오류 시 이미지
                .apply(RequestOptions().centerCrop())
                .into(itemView.findViewById(R.id.imgCatDetailPhoto))
        }
    }
}