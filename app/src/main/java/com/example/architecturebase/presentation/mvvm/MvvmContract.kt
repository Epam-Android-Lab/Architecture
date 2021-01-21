package com.example.architecturebase.presentation.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.architecturebase.data.network.model.Post

interface MvvmContract {
    interface IViewModel{
        val posts: LiveData<List<Post>>
        val errors: LiveData<Throwable>
        fun loadPosts()
    }
}