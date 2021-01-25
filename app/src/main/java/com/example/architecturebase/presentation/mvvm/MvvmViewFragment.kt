package com.example.architecturebase.presentation.mvvm

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.architecturebase.databinding.FragmentMvvmViewBinding
import com.example.architecturebase.domain.Post
import com.example.architecturebase.presentation.adapter.MainAdapter

class MvvmViewFragment : Fragment(){

    private val viewModel  by viewModels<MvvmViewModel>()
    private val mainAdapter = MainAdapter()
    private var _binding: FragmentMvvmViewBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        lifecycle.addObserver(viewModel)
    }

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

        viewModel.posts.observe(viewLifecycleOwner,{
            showNewPosts(it)
        })

        viewModel.errors.observe(viewLifecycleOwner, Observer<Throwable>{
            showError(it)
        })

        binding.listSRL.setOnRefreshListener {
            onRefreshList()
        }

    }

    private fun showNewPosts(posts: List<Post>) {
        mainAdapter.items = posts
        binding.listSRL.isRefreshing = false
    }

    private fun onRefreshList(){
        mainAdapter.items = emptyList()
        viewModel.loadPosts()
    }

    private fun showError(t: Throwable) {
        Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
        binding.listSRL.isRefreshing = false
    }

}



