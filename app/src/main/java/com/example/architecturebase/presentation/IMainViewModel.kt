package com.example.architecturebase.presentation

import androidx.lifecycle.MutableLiveData
import com.example.architecturebase.domain.models.Post

interface IMainViewModel {
    val recyclerData: MutableLiveData<List<Post>>
    fun loadData()
}