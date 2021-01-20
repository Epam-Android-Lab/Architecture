package com.example.architecturebase.presentation.mvp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.architecturebase.presentation.adapter.MainAdapter
import com.example.architecturebase.databinding.FragmentMvpViewBinding
import com.example.architecturebase.domain.Post

class MvpViewFragment : Fragment(), MvpContract.IView {

    private val presenter: MvpContract.IPresenter = MvpPresenter(this)
    private val mainAdapter = MainAdapter()
    private var _binding: FragmentMvpViewBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMvpViewBinding.inflate(inflater, container, false)
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

        presenter.loadNewPosts()

        binding.listSRL.setOnRefreshListener {
            presenter.loadNewPosts()
        }
    }

    override fun showNewPosts(posts: List<Post>) {
        mainAdapter.items = posts
        binding.listSRL.isRefreshing = false
    }

    override fun showError() {
        Toast.makeText(context, "error", Toast.LENGTH_SHORT).show()
        binding.listSRL.isRefreshing = false
    }

}