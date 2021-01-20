package com.example.architecturebase.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.architecturebase.domain.entities.Post
import com.example.architecturebase.presentation.contract.PostContract
import com.example.architecturebase.presentation.presenter.PostPresenter

class PostsViewModel : ViewModel() {

    private val presenter: PostContract.IPostPresenter = PostPresenter(this)

    var postsLiveData: MutableLiveData<List<Post>>? = null
    var refreshLiveData: MutableLiveData<Boolean> = MutableLiveData()

    fun getPosts(): MutableLiveData<List<Post>> {
        if(postsLiveData == null) {
            postsLiveData = MutableLiveData()
            loadPosts()
        }
        return postsLiveData as MutableLiveData<List<Post>>
    }

    fun getRefreshLiveDataObserver() = refreshLiveData

    fun loadPosts() {
        presenter.getNews()
    }

}