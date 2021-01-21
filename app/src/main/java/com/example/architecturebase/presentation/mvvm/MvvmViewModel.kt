package com.example.architecturebase.presentation.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.architecturebase.data.Repository
import com.example.architecturebase.domain.UsesCasesGetPosts

import com.example.architecturebase.data.network.model.Post

class MvvmViewModel : ViewModel(), MvvmContract.IViewModel {

    private val getUsesCasesGetPosts = UsesCasesGetPosts(Repository())

    private val _posts: MutableLiveData<List<Post>> = MutableLiveData()
    private val _errors: MutableLiveData<Throwable> = MutableLiveData()

    override val posts: MutableLiveData<List<Post>> = _posts
    override val errors: MutableLiveData<Throwable> = _errors

    override fun loadPosts() {

   //     getUsesCasesGetPosts.getPosts(posts, errors)

    }
}