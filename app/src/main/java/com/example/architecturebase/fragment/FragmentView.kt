package com.example.architecturebase.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.architecturebase.MvpContract
import com.example.architecturebase.MvpPresenter
import com.example.architecturebase.R
import com.example.architecturebase.adapter.MainAdapter
import com.example.architecturebase.databinding.FragmentViewBinding
import com.example.architecturebase.network.model.Post
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

    override fun showToast(t: Throwable) {
        Toast.makeText(this.context, t.message, Toast.LENGTH_SHORT).show()
    }
}