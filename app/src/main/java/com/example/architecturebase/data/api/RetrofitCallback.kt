package com.example.architecturebase.data.api

import com.example.architecturebase.domain.entities.Post

// создал свой callback для ретрофита
interface RetrofitCallback {
    fun onSuccessRequest(news: List<Post>)
    fun onFailedRequest(msg: String)
}