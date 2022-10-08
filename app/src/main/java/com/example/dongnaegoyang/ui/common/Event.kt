package com.example.dongnaegoyang.ui.common

import androidx.lifecycle.Observer

class Event<T>(private val content: T) {
    // 데이터 소비 여부
    private var hasBeenHandled = false

    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }
}

// LiveData 의 데이처가 변경되었는지 확인
// null 아닐 때만 데이터 전달
class EventObserver<T>(private val onEventUnhandledContent: (T) -> Unit): Observer<Event<T>> {
    override fun onChanged(event: Event<T>?) {
        event?.getContentIfNotHandled()?.let {
            onEventUnhandledContent(it)
        }
    }
}
