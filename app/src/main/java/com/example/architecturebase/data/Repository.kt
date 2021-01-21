package com.example.architecturebase.data


import com.example.architecturebase.data.network.IPostApi
import com.example.architecturebase.data.network.model.Post
import com.example.architecturebase.domain.RepositoryI
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

import com.example.architecturebase.domain.Post as PostEntity

class Repository : RepositoryI {

    private val postApi: IPostApi?

    init {

        val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                .callTimeout(REQUEST_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .build()

        val retrofit = Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()

        postApi = retrofit.create(IPostApi::class.java)

    }

    override fun getPosts(callback: (List<PostEntity>) -> Unit, errorCallback: (Throwable) -> Unit) {
        postApi?.getPosts()?.enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.isSuccessful) {
                    response.body()?.let { posts ->
                        callback(posts.map {
                            PostEntity(it.id, it.title, it.body)
                        })
                    }
                } else {
                    callback(ArrayList())
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                errorCallback(t)
            }
        })
    }

    companion object {
        private const val REQUEST_TIMEOUT_SECONDS = 5L
    }

}