package com.example.architecturebase

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.architecturebase.network.model.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModelMvvm : MvvmContract, ViewModel() {


    override val listPosts: MutableLiveData<List<Post>> = MutableLiveData()

    override val errorMessage: MutableLiveData<Throwable> = MutableLiveData()

    override fun getPosts() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val r = Repository().getData().getPosts()
                listPosts.postValue(r.result)
            } catch (e: Exception) {
                errorMessage.postValue(e)
            }
        }
    }
}