package com.example.architecturebase.domain.usecases

import com.example.architecturebase.data.repos.NewsRepoImpl
import com.example.architecturebase.domain.entities.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoadNewsUseCase() {

    private val repo: NewsRepoImpl = NewsRepoImpl()

    fun loadNews(
        funSetNews: (List<Post>) -> Unit,
        funSetRefresh: (Boolean) -> Unit,
        funOnFailed: (String) -> Unit
    ) {
        repo.getNews().enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.isSuccessful) {
                    response.body()?.let { posts ->
                        funSetNews(filterNews(posts))
                        funSetRefresh(false)
                    }
                } else {
                    funOnFailed("Oops.. something went wrong with response code: ${response.code()}")
                    funSetRefresh(false)
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                funOnFailed("Oops.. something went wrong!")
                funSetRefresh(false)
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