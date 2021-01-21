package com.example.architecturebase.data.network

import com.example.architecturebase.data.network.model.Post
import retrofit2.Call
import retrofit2.http.GET

interface IPostApi {
    @GET("/posts")
    fun getPosts(): Call<List<Post>>
}
