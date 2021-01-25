package com.example.architecturebase.presentation.mvvm

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.architecturebase.domain.Post

interface MvvmContract : LifecycleObserver{
        val posts: LiveData<List<Post>>
        val errors: LiveData<Throwable>
        fun loadPosts()
}
