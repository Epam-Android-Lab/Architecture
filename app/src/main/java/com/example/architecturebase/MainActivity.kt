package com.example.architecturebase

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.architecturebase.presentation.view.FragmentView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_main)
        super.onCreate(savedInstanceState)
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment, FragmentView())
            .commit()

    }
}