package com.example.architecturebase.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.architecturebase.R
import com.example.architecturebase.presentation.mvvm.MvvmViewFragment

class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val fragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) ?: MvvmViewFragment()

        savedInstanceState ?: supportFragmentManager.beginTransaction().apply {

            setReorderingAllowed(true)
            add(R.id.fragmentContainerView, fragment)
            commit()

        }
        setContentView(R.layout.activity_main)
    }

}