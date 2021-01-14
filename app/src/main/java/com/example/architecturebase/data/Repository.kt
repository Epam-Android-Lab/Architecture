package com.example.architecturebase.data

import com.example.architecturebase.network.IPostApi
import com.example.architecturebase.network.model.Post
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class Repository {

    private var postApi: IPostApi? = null

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

    fun getPosts(callback: (List<Post>) -> Unit) {
        postApi?.getPosts()?.enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.isSuccessful) {
                    response.body()?.let { posts ->
                        callback(posts)
                    }
                }
                else{
                    callback(ArrayList())
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                callback(ArrayList())
            }
        })
    }

    companion object {
        private const val REQUEST_TIMEOUT_SECONDS = 5L
    }

}