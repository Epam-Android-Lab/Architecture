package com.example.architecturebase.data.repos

import com.example.architecturebase.domain.entities.Post
import com.example.architecturebase.domain.repos.NewsRepo
import com.example.architecturebase.domain.usecases.LoadNewsUseCase
import com.example.architecturebase.presentation.contract.MvpContract
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsRepoImpl(private val mvpView: MvpContract.IView) : NewsRepo {

    private val loadNewsUseCase: LoadNewsUseCase = LoadNewsUseCase()

    override fun getNews() {
        return loadNewsUseCase.loadNews().enqueue(object : Callback<List<Post>> {
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
                        mvpView.setItems(processedPosts)
                        mvpView.setRefreshing(false)
                    }
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                mvpView.showToast(t)
                t.printStackTrace()
                mvpView.setRefreshing(false)
            }
        })
    }

}