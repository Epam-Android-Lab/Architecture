package com.example.architecturebase.domain

import com.example.architecturebase.data.network.IPostApi
import com.example.architecturebase.data.network.model.CovidStatistic
import io.reactivex.rxjava3.core.Single

interface RepositoryI {
    fun getPosts(callback: (List<Post>) -> Unit, errorCallback: (Throwable) -> Unit)
    fun getCovidStatistic(): Single<List<CovidStatistic>>
}