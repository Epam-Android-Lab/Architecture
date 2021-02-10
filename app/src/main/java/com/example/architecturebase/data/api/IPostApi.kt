package com.example.architecturebase.data.api

import com.example.architecturebase.domain.entities.Post
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface IPostApi {

    @GET("/posts")
    suspend fun getPosts(): Response<List<Post>>

    @POST("/posts")
    suspend fun pushPost(
        @Body post: Post
    ): Response<Post>
}
