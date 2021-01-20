package com.example.architecturebase.presentation

import com.example.architecturebase.data.repositories.RemoteRepository
import com.example.architecturebase.domain.models.Post
import com.example.architecturebase.domain.usecases.GetPostsUseCase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MvpPresenter(private val mvpView: MvpContract.IView) : MvpContract.IPresenter {

    override fun loadPosts() {
        GetPostsUseCase(RemoteRepository()).execute().enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        mvpView.setData(it)
                    }
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                mvpView.showFailure(t)
            }
        })
    }
}