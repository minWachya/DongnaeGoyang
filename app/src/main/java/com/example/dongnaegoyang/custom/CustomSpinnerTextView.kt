package com.example.dongnaegoyang.custom

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.dongnaegoyang.R

// CatAdd2, 3에 쓰이는 Spinner 처럼 생긴 TextView
class CustomSpinnerTextView : ConstraintLayout, View.OnClickListener  {
    // 커스텀 뷰 안에 들어가는 아이템
    lateinit var textView : TextView
    // 믈릭 리스너
    private var myOnCustomSTViewClickListener: OnCustomSTViewClickListener? = null

    // 클릭 리스너를 전달할 함수를 담은 인터페이스
    interface OnCustomSTViewClickListener { fun onCustomSTViewClick(view: View?) }
    // 전달받은 클릭 리스너 달기
    fun setOnCustomSTViewClickListener(mListener: OnCustomSTViewClickListener) {
        myOnCustomSTViewClickListener = mListener
    }

    // 생성자
    constructor(context: Context?) : super(context!!){
        init(context)
    }
    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs){
        init(context)
        getAttrs(attrs)
    }

    // 초기화
    private fun init(context:Context?) {
        val view = LayoutInflater.from(context).inflate(R.layout.custom_spinner_text_view,this,false)
        addView(view)
        textView = view.findViewById(R.id.custom_text_view)

        textView.setOnClickListener(this@CustomSpinnerTextView)
    }

    // 속성 가져오기
    private fun getAttrs(attrs: AttributeSet?){
        val typedArray = context.obtainStyledAttributes(attrs,
            R.styleable.CustomSpinnerTextView)
        setTypeArray(typedArray)
    }

    // 속성 사용하기
    private fun setTypeArray(typedArray : TypedArray) {
        // 텍스트 내용: CustomSpinnerTextView 이름으로 만든 attrs.xml 속성중 text 참조
        val text = typedArray.getText(R.styleable.CustomSpinnerTextView_text)
        textView.text = text

        // 텍스트 힌트: CustomSpinnerTextView 이름으로 만든 attrs.xml 속성중 hint 참조
        val hint = typedArray.getText(R.styleable.CustomSpinnerTextView_hint)
        textView.hint = hint

        typedArray.recycle()
    }

    // 클릭 효과 방샐
    override fun onClick(v: View?) {
        myOnCustomSTViewClickListener!!.onCustomSTViewClick(v)
    }
}