package com.example.architecturebase

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.architecturebase.adapter.MainAdapter
import com.example.architecturebase.databinding.ActivityMainBinding
import com.example.architecturebase.network.IPostApi
import com.example.architecturebase.network.INetworkConnection
import com.example.architecturebase.network.ResponseUseCase
import com.example.architecturebase.network.ResponseContract
import com.example.architecturebase.network.model.DefaultConnection
import com.example.architecturebase.network.model.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : FragmentActivity(), MVPContract.IView {

    private val binding by lazy {
        val bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)
        bind
    }
    private val mainAdapter = MainAdapter()
    private val INetworkConnection: INetworkConnection = DefaultConnection()
    private val local: ResponseContract = ResponseUseCase()

    private val postApi = INetworkConnection.getRetrofit().create(IPostApi::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.mainRV.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = mainAdapter
        }
        binding.listSRL.isRefreshing = true
        postApi.getPosts().enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.isSuccessful) {
                    mainAdapter.items = local.onResponse(call, response)
                    binding.listSRL.isRefreshing = false
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
                t.printStackTrace()
                binding.listSRL.isRefreshing = false
            }
        })

        binding.listSRL.setOnRefreshListener {
            mainAdapter.items = emptyList()

            postApi.getPosts().enqueue(object : Callback<List<Post>> {
                override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                    if (response.isSuccessful) {
                        mainAdapter.items = local.onResponse(call, response)
                        binding.listSRL.isRefreshing = false
                    }
                }

                override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                    Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
                    t.printStackTrace()
                    binding.listSRL.isRefreshing = false
                }
            })
        }
    }
}