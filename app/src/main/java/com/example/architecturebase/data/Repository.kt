package com.example.architecturebase.data


import com.example.architecturebase.data.network.ICovidStatisticApi
import com.example.architecturebase.data.network.IPostApi
import com.example.architecturebase.data.network.model.CovidStatistic
import com.example.architecturebase.data.network.model.Post
import com.example.architecturebase.domain.RepositoryI
import io.reactivex.rxjava3.core.Single
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import com.example.architecturebase.domain.Post as PostEntity


class Repository : RepositoryI {

    private val postApi: IPostApi?
    private val covidApi: ICovidStatisticApi

    init {

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .callTimeout(REQUEST_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .build()

        val retrofitPost = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        val retrofitCovid = Retrofit.Builder()
            .baseUrl("https://covid-19-data.p.rapidapi.com")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(okHttpClient)
            .build()

        postApi = retrofitPost.create(IPostApi::class.java)
        covidApi = retrofitCovid.create(ICovidStatisticApi::class.java)

    }

    override fun getPosts(callback: (List<PostEntity>) -> Unit, errorCallback: (Throwable) -> Unit) {
        postApi?.getPosts()?.enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.isSuccessful) {
                    response.body()?.let { posts ->
                        callback(posts.map {
                            PostEntity(it.id, it.title, it.body)
                        })
                    }
                } else {
                    callback(ArrayList())
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                errorCallback(t)
            }
        })
    }

    override fun getCovidStatistic(): Single<List<CovidStatistic>> = covidApi.getCovidStatistic()

    companion object {
        private const val REQUEST_TIMEOUT_SECONDS = 5L
    }

}