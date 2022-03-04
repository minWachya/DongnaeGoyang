package com.example.dongnaegoyang.cat_detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.dongnaegoyang.R

// 고양이 상세: 정보 탭 - 나도 관심 좀... 어댑터
class CatDetailInfoAnotherCatAdapter  : RecyclerView.Adapter<CatDetailInfoAnotherCatAdapter.ViewHolder>() {
    var cats = ArrayList<CatDetail>()  // 고양이 배열

    // 고양이 생김새 배열: 몸집, 코숏, 귀, 꼬리, 수염
    val arrImgSize = CatDetailArr.arrImgSize
    val arrImgFur = CatDetailArr.arrImgFur
    val arrImgEar = CatDetailArr.arrImgEar
    val arrImgTail = CatDetailArr.arrImgTail
    val arrImgWhisker = CatDetailArr.arrImgWhisker

    // 뷰홀더 생성
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatDetailInfoAnotherCatAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_cat_detail_info_another_cat, parent, false)
        return ViewHolder(itemView)
    }

    // position 번째 아이템 설정하기
    override fun onBindViewHolder(holder: CatDetailInfoAnotherCatAdapter.ViewHolder, position: Int) {
        val cat = cats[position]
        holder.setItem(cat)
    }

    // 아이템 갯수 리턴
    override fun getItemCount() = cats.size

    // 고양이 생김새 로드하기
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setItem(cat: CatDetail) {
            // 코숏, 몸집, 귀, 꼬리, 수염
            itemView.findViewById<ImageView>(R.id.item_imgCatFur).setImageResource(arrImgFur[cat.size][cat.fur])
            itemView.findViewById<ImageView>(R.id.item_imgCatSize).setImageResource(arrImgSize[cat.size])
            itemView.findViewById<ImageView>(R.id.item_imgCatEar).setImageResource(arrImgEar[cat.fur][cat.ear])
            itemView.findViewById<ImageView>(R.id.item_imgCatTail).setImageResource(arrImgTail[cat.fur][cat.tail])
            itemView.findViewById<ImageView>(R.id.item_imgCatWhisker).setImageResource(arrImgTail[cat.fur][cat.tail])
        }
    }
}