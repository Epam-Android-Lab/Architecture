package com.example.architecturebase.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit

interface INetworkConnection {
        fun getHTTPClient(): OkHttpClient
        fun getRetrofit(): Retrofit
}