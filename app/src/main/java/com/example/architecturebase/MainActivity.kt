package com.example.architecturebase

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.architecturebase.mvvm.MvvmViewFragment

class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.beginTransaction().apply {

            setReorderingAllowed(true)
            add(R.id.fragmentContainerView, MvvmViewFragment())
            commit()

        }
        setContentView(R.layout.activity_main)
    }

}