package com.example.architecturebase.mvvm

import androidx.lifecycle.MutableLiveData
import com.example.architecturebase.mvvm.notifier.INotifier
import com.example.architecturebase.network.IPostApi
import com.example.architecturebase.network.model.Post

interface MvvmContract {
    interface IViewModel{
        val posts: MutableLiveData<List<Post>>
        fun loadPosts()
    }
}