package com.example.architecturebase.mvvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.architecturebase.adapter.MainAdapter
import com.example.architecturebase.databinding.FragmentMvvmViewBinding
import com.example.architecturebase.network.model.Post

class MvvmViewFragment : Fragment(){

    private var viewModel: MvvmViewModel = MvvmViewModel()
    private val mainAdapter = MainAdapter()
    private var _binding: FragmentMvvmViewBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMvvmViewBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.mainRV.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mainAdapter
        }
        binding.listSRL.isRefreshing = true

        viewModel.loadPosts()

        viewModel.posts.observe(viewLifecycleOwner){
            showNewPosts(it)
        }

        binding.listSRL.setOnRefreshListener {
            setOnRefreshListener()
        }

    }

    private fun showNewPosts(posts: List<Post>) {
        mainAdapter.items = posts
        binding.listSRL.isRefreshing = false
    }

    private fun setOnRefreshListener(){
        mainAdapter.items = emptyList()
        viewModel.loadPosts()
    }

    fun showError() {
        Toast.makeText(context, "error", Toast.LENGTH_SHORT).show()
        binding.listSRL.isRefreshing = false
    }

}



