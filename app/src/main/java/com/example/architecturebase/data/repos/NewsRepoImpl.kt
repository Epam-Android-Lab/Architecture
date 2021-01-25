package com.example.architecturebase.data.repos

import com.example.architecturebase.data.api.RetrofitInstance
import com.example.architecturebase.domain.entities.Post
import com.example.architecturebase.domain.repos.NewsRepo
import retrofit2.Call

class NewsRepoImpl() : NewsRepo {

    private val postApi = RetrofitInstance.getRetrofitInstance()

    override fun getNews(): Call<List<Post>> {
        return postApi.getPosts()
    }
}