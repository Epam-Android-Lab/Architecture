package com.example.architecturebase.domain.usecases

import com.example.architecturebase.data.api.RetrofitInstance
import com.example.architecturebase.domain.entities.Post
import com.example.architecturebase.presentation.contract.MvpContract
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoadNewsUseCase() {
    private val postApi = RetrofitInstance.getRetrofitInstance()

    // получаем список
    fun loadNews(): Call<List<Post>> = postApi.getPosts()
}