package com.example.architecturebase.presentation.mvvm

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.architecturebase.data.network.model.CovidStatistic
import com.example.architecturebase.domain.Post

interface MvvmContract : LifecycleObserver{
        val posts: LiveData<List<Post>>
        val errors: LiveData<Throwable>
        val covidStatistic: LiveData<CovidStatistic>
        fun loadPosts()
        fun loadCovidStatistic()
}
