package com.example.dongnaegoyang.custom

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.dongnaegoyang.R

// CatAdd2, 3에 쓰이는 Spinner 처럼 생긴 TextView
class CustomSpinnerTextView : ConstraintLayout {
    // 커스텀 뷰 안에 들어가는 아이템
    lateinit var textView : TextView

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
}