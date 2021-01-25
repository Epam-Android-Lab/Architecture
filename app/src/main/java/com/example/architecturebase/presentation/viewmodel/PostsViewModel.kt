package com.example.architecturebase.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.architecturebase.data.api.RetrofitCallback
import com.example.architecturebase.domain.entities.Post
import com.example.architecturebase.domain.usecases.LoadNewsUseCase
import com.example.architecturebase.presentation.contract.PostContract

// вьюмодель
class PostsViewModel : PostContract, ViewModel() {

    // для постов
    override var newsList: MutableLiveData<List<Post>> = MutableLiveData()
    // для ошибок
    override var failMsg:  MutableLiveData<String> = MutableLiveData()

    override fun getNews() {
        LoadNewsUseCase().loadNews(object : RetrofitCallback {
            override fun onSuccessRequest(news: List<Post>) {
                newsList.value = news
            }

            override fun onFailedRequest(msg: String) {
                failMsg.value = msg
            }

        })
    }

}