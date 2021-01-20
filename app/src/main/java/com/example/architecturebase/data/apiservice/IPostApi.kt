package com.example.architecturebase.data.apiservice

import com.example.architecturebase.domain.models.Post
import retrofit2.Call
import retrofit2.http.GET

interface IPostApi {
    @GET("/posts")
    fun getPosts(): Call<List<Post>>
}
