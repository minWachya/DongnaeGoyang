package com.example.dongnaegoyang.ui.cat_add

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.dongnaegoyang.R
import com.example.dongnaegoyang.databinding.FragmentCatAdd3Binding

// 고양이 추가 페이지-3단계: 사진 어댑터
class CatAddPhotoAdapter(val binding: FragmentCatAdd3Binding) : RecyclerView.Adapter<CatAddPhotoAdapter.ViewHolder>() {
    var imgUris = ArrayList<String>()

    var deletePhotoUrl = ArrayList<String>()

    // 뷰홀더 생성
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatAddPhotoAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_cat_add_photo, parent, false)

        return ViewHolder(itemView)
    }

    // position 번째 아이템 설정하기
    override fun onBindViewHolder(holder: CatAddPhotoAdapter.ViewHolder, position: Int) {
        val uri = imgUris[position]
        holder.setItem(uri)
    }

    // 아이템 갯수 리턴
    override fun getItemCount() = imgUris.size

    // 아이템 삭제
    fun removeData(position: Int) {
        deletePhotoUrl.add(imgUris[position])
        // 아이템 삭제
        imgUris.removeAt(position)
        notifyItemRemoved(position)
        // 현재 선택된 사진 개수 변경
        binding.tvSelectCount.text = itemCount.toString()
    }

    // 사진 로드하기
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setItem(url: String) {
            // 이미지 로드
            Glide.with(itemView)
                .load(url)
                .error(R.drawable.ic_launcher_background)                  // 오류 시 이미지
                .apply(RequestOptions().centerCrop())
                .into(itemView.findViewById(R.id.imgCatAddPhoto))

            // 삭제 버튼
            itemView.findViewById<ImageView>(R.id.imgCatAddClose).setOnClickListener {
                removeData(this.layoutPosition)
            }
        }
    }
}