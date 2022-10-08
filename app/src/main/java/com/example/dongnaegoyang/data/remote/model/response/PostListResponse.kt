package com.example.dongnaegoyang.data.remote.model.response

/*
"postList": [
            {
                "postIdx": 1,
                "createdTime": "2022-10-07T15:56:42.806412",
                "writer": {
                    "kakaoId": 2154390251,
                    "nickname": "박지혜"
                },
                "isWriter": true,
                "content": "냥냥이 오늘 밥줬어요"
            }
        ]
    }
*/
data class PostListResponse(val postList: List<Post>)

data class Post(
    val postIdx: Int,
    val createdTime: String,
    val writer: User,
    val isWriter: Boolean,
    val content: String
)
