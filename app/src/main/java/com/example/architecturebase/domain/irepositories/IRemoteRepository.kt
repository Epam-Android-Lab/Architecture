package com.example.architecturebase.domain.irepositories

import com.example.architecturebase.domain.models.Post
import retrofit2.Call

interface IRemoteRepository {
    fun getPosts(): Call<List<Post>>
}