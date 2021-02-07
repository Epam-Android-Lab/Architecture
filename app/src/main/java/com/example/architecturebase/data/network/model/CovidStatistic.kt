package com.example.architecturebase.data.network.model

import com.google.gson.annotations.SerializedName

data class CovidStatistic(
    @SerializedName("confirmed")
    val confirmed: Long,

    @SerializedName("critical")
    val critical: Long,

    @SerializedName("deaths")
    val deaths: Long,

    @SerializedName("lastChange")
    val lastChange: String,

    @SerializedName("lastUpdate")
    val lastUpdate: String,

    @SerializedName("recovered")
    val recovered: Long
)