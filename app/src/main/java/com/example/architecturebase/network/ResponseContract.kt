package com.example.architecturebase.network

import com.example.architecturebase.network.model.Post
import retrofit2.Call
import retrofit2.Response

interface ResponseContract {
    fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>): List<Post>
}