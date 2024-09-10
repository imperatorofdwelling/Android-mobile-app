package com.imperatorofdwelling.android.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.imperatorofdwelling.android.databinding.ActivityMainBinding
import com.imperatorofdwelling.android.presentation.ui.city_selection.CitySelectionFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction()
            .replace(binding.mainFragment.id, CitySelectionFragment.newInstance()).commit()
    }
}