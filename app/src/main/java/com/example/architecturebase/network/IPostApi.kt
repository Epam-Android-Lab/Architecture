package com.example.architecturebase.network

import com.example.architecturebase.network.model.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST

interface IPostApi {
    @GET("/posts")
    fun getPosts(): Call<List<Post>>

}
