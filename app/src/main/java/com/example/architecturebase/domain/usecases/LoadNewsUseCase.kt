package com.example.architecturebase.domain.usecases

import com.example.architecturebase.data.api.IPostApi
import com.example.architecturebase.data.api.RetrofitCallback
import com.example.architecturebase.data.api.RetrofitInstance
import com.example.architecturebase.data.repos.NewsRepoImpl
import com.example.architecturebase.domain.entities.Post
import com.example.architecturebase.domain.repos.NewsRepo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoadNewsUseCase {

    private val repo: NewsRepo = NewsRepoImpl()

    fun loadNews(callback: RetrofitCallback) {
        repo.getNews().enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.isSuccessful) {
                    response.body()?.let { posts ->
                        callback.onSuccessRequest(filterNews(posts))
                    }
                } else {
                    callback.onFailedRequest(
                        "Oops.. something went wrong with code: ${response.code()}!"
                    )
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                t.message?.let { callback.onFailedRequest(it) }
            }
        })
    }

    private fun filterNews(news: List<Post>): List<Post> {
        return news.filter {
            !it.title.startsWith("H")
        }.map {
            it.copy(title = it.title + "appendix")
        }.sortedBy {
            it.title
        }.subList(0, news.size - 3)
    }
}