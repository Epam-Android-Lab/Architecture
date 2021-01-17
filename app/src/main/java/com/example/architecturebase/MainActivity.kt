package com.example.architecturebase

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.architecturebase.adapter.MainAdapter
import com.example.architecturebase.databinding.ActivityMainBinding
import com.example.architecturebase.network.IPostApi
import com.example.architecturebase.network.model.Post
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        val bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)
        bind
    }

    private val mainAdapter = MainAdapter()

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.recyclerData.observe(this) {
            binding.listSRL.isRefreshing = false
            mainAdapter.items = it
        }

        binding.mainRV.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = mainAdapter
        }

        binding.listSRL.isRefreshing = true

        viewModel.loadData()

        binding.listSRL.setOnRefreshListener {
            viewModel.loadData()
        }
    }
}