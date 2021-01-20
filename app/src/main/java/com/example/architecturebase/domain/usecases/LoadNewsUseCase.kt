package com.example.architecturebase.domain.usecases

import com.example.architecturebase.data.api.IPostApi
import com.example.architecturebase.data.api.RetrofitInstance
import com.example.architecturebase.domain.entities.Post
import retrofit2.Call

class LoadNewsUseCase {

    private val postApi = RetrofitInstance.getRetrofit()

    fun loadNews(): Call<List<Post>> = postApi.getPosts()
}