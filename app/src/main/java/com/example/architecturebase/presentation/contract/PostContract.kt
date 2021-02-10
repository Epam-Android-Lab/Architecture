package com.example.architecturebase.presentation.contract

import androidx.lifecycle.MutableLiveData
import com.example.architecturebase.domain.entities.Post

// создал контракт для вью модели
interface PostContract {

    var newsList: MutableLiveData<List<Post>>
    var failMsg: MutableLiveData<String>
    fun getNews()
    fun pushPost()

}