package com.example.architecturebase.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.architecturebase.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_main)
        super.onCreate(savedInstanceState)
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment, FragmentView())
            .commit()

    }
}