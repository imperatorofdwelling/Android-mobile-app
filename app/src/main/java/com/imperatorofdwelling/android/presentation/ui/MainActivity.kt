package com.imperatorofdwelling.android.presentation.ui

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.res.colorResource
import androidx.core.content.ContextCompat
import cafe.adriel.voyager.navigator.Navigator
import com.imperatorofdwelling.android.R
import com.imperatorofdwelling.android.databinding.ActivityMainBinding
import com.imperatorofdwelling.android.presentation.ui.city_selection.CitySelectionFragment
import com.imperatorofdwelling.android.presentation.ui.sign_up.SignUpScreen
import com.imperatorofdwelling.android.presentation.ui.theme.MyApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    //private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding = ActivityMainBinding.inflate(layoutInflater)
        //setContentView(binding.root)
//        supportFragmentManager.beginTransaction()
//            .replace(binding.mainFragment.id, CitySelectionFragment.newInstance()).commit()
        setContent{
            MyApplicationTheme {
                Navigator(SignUpScreen())
            }
        }
    }
}