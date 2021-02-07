package com.example.architecturebase

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.architecturebase.adapter.MainAdapter
import com.example.architecturebase.databinding.FragmentBinding


class Fragment : Fragment(R.layout.fragment) {

    private val mvvmModelView: MvvmContract = ViewModelMvvm()
    private val connectivityManager: ConnectivityManager by lazy {
        val r = requireActivity().getSystemService(Context.CONNECTIVITY_SERVICE)
        r as ConnectivityManager
    }

    private var _binding: FragmentBinding? = null
    private val binding get() = _binding!!

    private val mainAdapter = MainAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentBinding.bind(view)
        binding.mainRV.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = mainAdapter
        }

        binding.listSRL.isRefreshing = true
        loadDataFromInternet()

        mvvmModelView.listPosts.observe(viewLifecycleOwner) {
            mainAdapter.items = it
            binding.listSRL.isRefreshing = false
        }

        mvvmModelView.errorMessage.observe(viewLifecycleOwner) { t -> showFailureLoadDataDialog(t) }

        binding.listSRL.setOnRefreshListener {
            mainAdapter.items = emptyList()
            loadDataFromInternet()

        }
    }

    private fun showFailureLoadDataDialog(t: Throwable) {
        t.printStackTrace()
        binding.listSRL.isRefreshing = false
        Toast.makeText(activity, t.message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun checkInternet(): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val nw = connectivityManager.activeNetwork ?: return false
            val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
            return when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            val nwInfo = connectivityManager.activeNetworkInfo ?: return false
            return nwInfo.isConnected
        }
    }

    private fun loadDataFromInternet() {
        if (checkInternet()) {
            mvvmModelView.getPosts()
        } else {
            binding.listSRL.isRefreshing = false
            Toast.makeText(activity, "connect internet needed", Toast.LENGTH_SHORT).show()
        }
    }
}