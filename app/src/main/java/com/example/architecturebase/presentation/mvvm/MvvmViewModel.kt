package com.example.architecturebase.presentation.mvvm

import androidx.lifecycle.*
import com.example.architecturebase.data.Repository
import com.example.architecturebase.data.network.model.CovidStatistic
import com.example.architecturebase.domain.Post
import com.example.architecturebase.domain.UsesCasesGetCovidStatistic

import com.example.architecturebase.domain.UsesCasesGetPosts
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class MvvmViewModel : ViewModel(), MvvmContract {

    private val usesCasesGetPosts = UsesCasesGetPosts(Repository())
    private val usesCasesGetCovidStatistic = UsesCasesGetCovidStatistic(Repository())
    private val _posts: MutableLiveData<List<Post>> = MutableLiveData()
    private val _errors: MutableLiveData<Throwable> = MutableLiveData()
    private val _covidStatistic: MutableLiveData<CovidStatistic> = MutableLiveData()
    override val posts: LiveData<List<Post>> = _posts
    override val errors: LiveData<Throwable> = _errors
    override val covidStatistic: LiveData<CovidStatistic> = _covidStatistic

    override fun loadPosts() {
        usesCasesGetPosts.getPosts({ posts ->
            _posts.postValue(posts)
        }, { t ->
            _errors.postValue(t)
        })
    }

    override fun loadCovidStatistic() {
        usesCasesGetCovidStatistic.getCovidStatistic()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { data ->
                    _covidStatistic.postValue(data)
                },
                {
                    _errors.postValue(it)
                }
            )
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        loadPosts()
        loadCovidStatistic()
    }
}