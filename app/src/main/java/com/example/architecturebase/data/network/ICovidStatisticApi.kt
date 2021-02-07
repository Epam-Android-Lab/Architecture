package com.example.architecturebase.data.network

import com.example.architecturebase.data.network.model.CovidStatistic
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Headers

interface ICovidStatisticApi {
    @GET("/totals")
    @Headers(
        "x-rapidapi-key: a7220fc634msh0ab76dcbb94bad3p1521a9jsna4d61d0cee62",
        "x-rapidapi-host: covid-19-data.p.rapidapi.com"
    )
    fun getCovidStatistic(): Single<List<CovidStatistic>>
}