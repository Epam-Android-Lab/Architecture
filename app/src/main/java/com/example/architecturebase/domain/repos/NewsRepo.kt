package com.example.architecturebase.domain.repos

import com.example.architecturebase.domain.entities.Post
import retrofit2.Call
import retrofit2.Response

interface NewsRepo {
    suspend fun getNews(): Response<List<Post>>
    suspend fun pushPost(post: Post)
}