package com.example.architecturebase.presentation.mvvm

import androidx.lifecycle.*
import com.example.architecturebase.data.Repository
import com.example.architecturebase.domain.UsesCasesGetPosts

import com.example.architecturebase.data.network.model.Post

class MvvmViewModel : LifecycleObserver, MvvmContract.IViewModel {

    private val usesCasesGetPosts = UsesCasesGetPosts(Repository())

    private val _posts: MutableLiveData<List<Post>> = MutableLiveData()
    private val _errors: MutableLiveData<Throwable> = MutableLiveData()

    override val posts: MutableLiveData<List<Post>> = _posts
    override val errors: MutableLiveData<Throwable> = _errors

    override fun loadPosts() {

        usesCasesGetPosts.getPosts({ posts ->

            _posts.value

        }, { t ->
            _errors.value
        })

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        loadPosts()
    }
}