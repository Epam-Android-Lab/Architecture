package com.example.architecturebase.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.architecturebase.presentation.adapter.MainAdapter
import com.example.architecturebase.databinding.ActivityMainBinding
import com.example.architecturebase.presentation.viewmodel.PostsViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainAdapter = MainAdapter()
    private lateinit var viewModel: PostsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        viewModel = ViewModelProvider(this).get(PostsViewModel::class.java)
        binding.mainRV.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = mainAdapter
        }

        viewModel.getPosts().observe(this, {
            if (it != null) {
                mainAdapter.items = it
            } else {
                Toast.makeText(this, "Oooops.. something went wrong", Toast.LENGTH_SHORT)
            }
        })

        viewModel.getRefreshLiveDataObserver().observe(this, {
            binding.listSRL.setOnRefreshListener {
                viewModel.loadPosts()
                binding.listSRL.isRefreshing = false
            }
        })

    }
}