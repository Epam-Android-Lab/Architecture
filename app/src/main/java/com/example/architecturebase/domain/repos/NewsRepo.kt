package com.example.architecturebase.domain.repos

import com.example.architecturebase.domain.entities.Post
import retrofit2.Call

interface NewsRepo {
    fun getNews(): Call<List<Post>>
}