package com.dentag.pulsometer.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dentag.pulsometer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        savedInstanceState ?: let {
            supportFragmentManager.beginTransaction()
                .add(binding.mainFragmentContainer.id, MainFragment())
                .commit()
        }
    }
}