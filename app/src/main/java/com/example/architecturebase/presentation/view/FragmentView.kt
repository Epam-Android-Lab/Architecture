package com.example.architecturebase.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.architecturebase.databinding.FragmentViewBinding
import com.example.architecturebase.presentation.adapter.MainAdapter
import com.example.architecturebase.presentation.contract.PostContract
import com.example.architecturebase.presentation.viewmodel.PostsViewModel

// добавил фрагмент
class FragmentView : Fragment() {

    private lateinit var binding: FragmentViewBinding
    private lateinit var mainAdapter: MainAdapter

    private val viewModel: PostContract = PostsViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentViewBinding.inflate(inflater, container, false)
        mainAdapter = MainAdapter()

        binding.mainRV.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mainAdapter
        }

        viewModel.getNews()

        binding.listSRL.isRefreshing = true

        viewModel.newsList.observe(viewLifecycleOwner, {
            mainAdapter.items = it
            binding.listSRL.isRefreshing = false
        })

        viewModel.failMsg.observe(viewLifecycleOwner, {
            showOnFailToast(it)
        })

        return binding.root
    }

    private fun showOnFailToast(msg: String) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
        binding.listSRL.isRefreshing = false
    }
}