package com.example.architecturebase.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.architecturebase.presentation.adapter.MainAdapter
import com.example.architecturebase.databinding.ActivityMainBinding

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