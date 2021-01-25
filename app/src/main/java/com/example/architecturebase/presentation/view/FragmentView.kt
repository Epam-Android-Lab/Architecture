package com.example.architecturebase.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.architecturebase.presentation.adapter.MainAdapter
import com.example.architecturebase.databinding.FragmentViewBinding
import com.example.architecturebase.domain.entities.Post
import com.example.architecturebase.presentation.presenter.MvpPresenter
import com.example.architecturebase.presentation.contract.MvpContract

// реализация view, в даном случае это фрагмент
class FragmentView : Fragment(), MvpContract.IView {

    private lateinit var binding: FragmentViewBinding
    private lateinit var presenter: MvpContract.IPresenter
    private lateinit var mainAdapter: MainAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentViewBinding.inflate(inflater, container, false)
        presenter = MvpPresenter(this)
        mainAdapter = MainAdapter()

        binding.mainRV.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mainAdapter
        }
        setRefreshing(true)
        presenter.getItemsFromNetwork()

        binding.listSRL.setOnRefreshListener {
            presenter.getItemsFromNetwork()
        }
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun setItems(items: List<Post>) {
        mainAdapter.items = items
    }

    override fun setRefreshing(flag: Boolean) {
        binding.listSRL.isRefreshing = flag
    }

    override fun showFailToast(msg: String) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
    }
}