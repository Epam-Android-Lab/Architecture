package com.example.architecturebase

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.architecturebase.adapter.MainAdapter
import com.example.architecturebase.network.model.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostsViewModel : ViewModel() {

    private val presenter: PostContract.IPostPresenter = PostPresenter()

    private val postApi = presenter.getPostsApi()

    private val adapter: MainAdapter = MainAdapter()

    var postsLiveData: MutableLiveData<List<Post>>? = null
    var refreshLiveData: MutableLiveData<Boolean> = MutableLiveData()

    fun getPosts(): MutableLiveData<List<Post>> {
        if(postsLiveData == null) {
            postsLiveData = MutableLiveData()
            loadPosts()
        }
        return postsLiveData!!
    }



    fun getRefreshLiveDataObserver() = refreshLiveData

    fun loadPosts() {
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
//                        mainAdapter.items = processedPosts
//                        binding.listSRL.isRefreshing = false
                        postsLiveData!!.value = processedPosts
                        refreshLiveData.value = false
                    }
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Toast.makeText(MainActivity().application.applicationContext, t.message, Toast.LENGTH_SHORT).show()
                t.printStackTrace()
                refreshLiveData.value = false
            }
        })
    }

}