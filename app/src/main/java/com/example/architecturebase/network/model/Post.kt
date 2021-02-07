package com.example.architecturebase.network.model

import com.google.gson.annotations.SerializedName

data class Post(

        @SerializedName("id")
        val id: Long,
        @SerializedName("title")
        val title: String,
        @SerializedName("intro")
        val intro: String,
)

data class DtfAnswer(
        @SerializedName("result")
        val result: List<Post>,
)