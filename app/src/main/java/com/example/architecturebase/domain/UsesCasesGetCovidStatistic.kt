package com.example.architecturebase.domain

import com.example.architecturebase.data.network.model.CovidStatistic
import io.reactivex.rxjava3.core.Single

class UsesCasesGetCovidStatistic(private val repository: RepositoryI) {
    fun getCovidStatistic() : Single<CovidStatistic>{
        return repository.getCovidStatistic().map {
            it[0]
        }
    }
}