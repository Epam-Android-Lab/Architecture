package com.example.architecturebase.data.repositories

import com.example.architecturebase.data.apiservice.IPostApi
import com.example.architecturebase.domain.models.Post
import com.example.architecturebase.domain.irepositories.IRemoteRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteRepository : IRemoteRepository {
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com")
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    private val postApi = retrofit.create(IPostApi::class.java)

    override fun getPosts(): Call<List<Post>> = postApi.getPosts()
}