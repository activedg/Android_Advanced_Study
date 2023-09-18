package com.example.naversearchcompose.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.naversearchcompose.navigation.MainNavHost
import com.example.naversearchcompose.ui.main.MainRoute
import com.example.naversearchcompose.ui.theme.NaverSearchComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NaverSearchComposeTheme {
                MainNavHost()
            }
        }
    }
}
