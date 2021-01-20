package com.example.architecturebase.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.architecturebase.data.apiservice.IPostApi
import com.example.architecturebase.data.repositories.RemoteRepository
import com.example.architecturebase.domain.models.Post
import com.example.architecturebase.domain.usecases.GetPostsUseCase
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class MainViewModel : ViewModel(), IMainViewModel {

    override val recyclerData = MutableLiveData<List<Post>>()

    override fun loadData() {
        GetPostsUseCase(RemoteRepository()).execute().enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.isSuccessful) {
                    response.body()?.let { posts ->
                        // logic starts
                        val processedPosts = posts.filter {
                            !it.title.startsWith("H")
                        }.map {
                            it.copy(title = it.title + "appendix")
                        }.sortedBy {
                            it.title
                        }.subList(0, posts.size - 3)
                        // logic ends
                        recyclerData.postValue(processedPosts)
                    }
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}