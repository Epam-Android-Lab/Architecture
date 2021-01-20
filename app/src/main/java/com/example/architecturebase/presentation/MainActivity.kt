package com.example.architecturebase.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.architecturebase.R
import com.example.architecturebase.presentation.mvp.MvpViewFragment

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