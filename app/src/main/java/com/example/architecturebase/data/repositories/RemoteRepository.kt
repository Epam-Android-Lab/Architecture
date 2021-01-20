package com.example.architecturebase.data.repositories

import com.example.architecturebase.data.apiservice.IPostApi
import com.example.architecturebase.domain.irepositories.IRemoteRepository
import com.example.architecturebase.domain.models.Post
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RemoteRepository : IRemoteRepository {
    companion object {
        private const val REQUEST_TIMEOUT_SECONDS = 5L
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .callTimeout(REQUEST_TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com")
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    private val postApi = retrofit.create(IPostApi::class.java)
    override fun getPosts(): Call<List<Post>> = postApi.getPosts()
}