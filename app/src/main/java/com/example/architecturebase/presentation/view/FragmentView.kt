package com.example.architecturebase.presentation.view

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
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

    private val connectivityManager: ConnectivityManager by lazy {
        requireActivity().getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager
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

        binding.listSRL.isRefreshing = true
        loadPosts()

        viewModel.pushPost()

        viewModel.getNews()

        viewModel.newsList.observe(viewLifecycleOwner, {
            mainAdapter.items = it
            binding.listSRL.isRefreshing = false
        })

        viewModel.failMsg.observe(viewLifecycleOwner, {
            showOnFailToast(it)
        })
        binding.listSRL.setOnRefreshListener {
            mainAdapter.items = listOf()
            loadPosts()
        }

        return binding.root
    }

    private fun showOnFailToast(msg: String) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
        binding.listSRL.isRefreshing = false
    }

    private fun loadPosts() {
        if (check()) {
            viewModel.getNews()
        } else {
            binding.listSRL.isRefreshing = false
            Toast.makeText(activity, "Please check internet connection..", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun check(): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val aN = connectivityManager.getNetworkCapabilities(network) ?: return false
            return when {
                aN.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                aN.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                aN.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            val networkInfo = connectivityManager.activeNetworkInfo ?: return false
            return networkInfo.isConnected
        }
    }
}