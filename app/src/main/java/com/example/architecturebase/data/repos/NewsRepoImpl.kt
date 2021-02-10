package com.example.architecturebase.data.repos

import com.example.architecturebase.data.api.RetrofitInstance
import com.example.architecturebase.domain.entities.Post
import com.example.architecturebase.domain.repos.NewsRepo
import retrofit2.Call
import retrofit2.Response

class NewsRepoImpl() : NewsRepo {

    private val postApi = RetrofitInstance.getRetrofitInstance()

    override suspend fun getNews(): Response<List<Post>> {
        return postApi.getPosts()
    }

    override suspend fun pushPost(post: Post) {
        postApi.pushPost(post)
    }
}