package com.example.architecturebase.presentation.mvvm

import androidx.lifecycle.*
import com.example.architecturebase.data.Repository
import com.example.architecturebase.domain.Post

import com.example.architecturebase.domain.UsesCasesGetPosts


class MvvmViewModel : ViewModel(), MvvmContract {

    private val usesCasesGetPosts = UsesCasesGetPosts(Repository())
    private val _posts: MutableLiveData<List<Post>> = MutableLiveData()
    private val _errors: MutableLiveData<Throwable> = MutableLiveData()
    override val posts: LiveData<List<Post>> = _posts
    override val errors: LiveData<Throwable> = _errors

    override fun loadPosts() {

        usesCasesGetPosts.getPosts({ posts ->

            _posts.postValue(posts)

        }, { t ->
            _errors.postValue(t)
        })

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        loadPosts()
    }
}