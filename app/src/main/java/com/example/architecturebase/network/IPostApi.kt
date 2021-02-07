package com.example.architecturebase.network

import com.example.architecturebase.network.model.DtfAnswer
import com.example.architecturebase.network.model.Post
import retrofit2.Call
import retrofit2.http.GET

interface IPostApi {
    @GET("timeline/gamedev/week?count=10")
    suspend fun getPosts(): DtfAnswer
}
