package com.example.architecturebase.data.repos

import com.example.architecturebase.data.api.RetrofitInstance
import com.example.architecturebase.domain.entities.Post
import com.example.architecturebase.domain.repos.NewsRepo
import com.example.architecturebase.domain.usecases.LoadNewsUseCase
import com.example.architecturebase.presentation.contract.MvpContract
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsRepoImpl() : NewsRepo {

    private val postApi = RetrofitInstance.getRetrofitInstance()


    override fun getNews(): Call<List<Post>> {
        return postApi.getPosts()
    }

}