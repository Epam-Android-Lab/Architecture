package com.example.architecturebase.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import com.example.architecturebase.R
import com.example.architecturebase.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)
        supportFragmentManager.beginTransaction().apply {
            setReorderingAllowed(true)
            add<DataListFragment>(R.id.fr_container, null)
            commit()
        }
    }
}