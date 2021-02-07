package com.example.architecturebase

import com.example.architecturebase.network.IPostApi

interface IRepository {

    suspend fun getData(): IPostApi
}