package com.example.architecturebase.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.architecturebase.presentation.adapter.MainAdapter
import com.example.architecturebase.databinding.FragmentDataListBinding
import com.example.architecturebase.domain.models.Post


class DataListFragment : Fragment(), MvpContract.IView {

    private val presenter: MvpContract.IPresenter = MvpPresenter(this)

    private val mainAdapter = MainAdapter()

    private val binding: FragmentDataListBinding by lazy {
        val tmpBinding = FragmentDataListBinding.inflate(layoutInflater)
        tmpBinding
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.mainRV.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mainAdapter
        }

        presenter.loadPosts()
    }

    override fun setData(data: List<Post>) {
        mainAdapter.items = data
    }

    override fun showFailure(failure: Throwable) {
        Toast.makeText(requireContext(), failure.message, Toast.LENGTH_SHORT).show()
        failure.printStackTrace()
    }
}