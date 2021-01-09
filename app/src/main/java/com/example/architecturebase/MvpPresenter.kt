package com.example.architecturebase

import android.widget.Toast
import com.example.architecturebase.network.RetrofitInstance
import com.example.architecturebase.network.model.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MvpPresenter(val mvpView: MvpContract.IView) : MvpContract.IPresenter {

    // получаем объект Retrofit'а
    private val postApi = RetrofitInstance.getRetrofitInstance()

    // получаем список
    override fun getItemsFromNetwork(){
        postApi.getPosts().enqueue(object : Callback<List<Post>> {
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