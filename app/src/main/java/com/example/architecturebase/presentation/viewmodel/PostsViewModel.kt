package com.example.architecturebase.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.architecturebase.data.api.RetrofitCallback
import com.example.architecturebase.domain.entities.Post
import com.example.architecturebase.domain.usecases.LoadNewsUseCase
import com.example.architecturebase.presentation.contract.PostContract
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// вьюмодель
class PostsViewModel : PostContract, ViewModel() {

    // для постов
    override var newsList: MutableLiveData<List<Post>> = MutableLiveData()
    // для ошибок
    override var failMsg:  MutableLiveData<String> = MutableLiveData()

    override fun getNews() {
        viewModelScope.launch(Dispatchers.IO) {
            try{
                val response = LoadNewsUseCase().loadNews()
                if(response.isSuccessful){
                    response.body()?.let {
                        newsList.postValue(it)
                    }
                }
            } catch (e: Exception) {
                newsList.postValue(listOf())
            }

        }

    }

    override fun pushPost() {
        viewModelScope.launch(Dispatchers.IO) {
            LoadNewsUseCase().pushPost()
        }
    }

}