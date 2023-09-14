package com.example.naversearchcompose.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.naversearchcompose.ui.MainContract.*

@Composable
fun MainRoute(
    viewModel: MainViewModel = hiltViewModel()
){
    val state : MainState by viewModel.state.collectAsStateWithLifecycle()
}