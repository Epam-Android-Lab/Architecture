package com.example.architecturebase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.architecturebase.network.model.Post

interface IMainViewModel {
    val recyclerData: MutableLiveData<List<Post>>
    fun loadData()
}