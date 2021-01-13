package com.example.architecturebase

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.architecturebase.adapter.MainAdapter
import com.example.architecturebase.databinding.ActivityMainBinding
import com.example.architecturebase.mvp.MvpContract
import com.example.architecturebase.mvp.MvpPresenter
import com.example.architecturebase.mvp.MvpViewFragment
import com.example.architecturebase.network.model.Post

class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.beginTransaction().apply {

            setReorderingAllowed(true)
            add(R.id.fragmentContainerView, MvpViewFragment())
            commit()

        }
        setContentView(R.layout.activity_main)
    }

}