package com.example.naversearchtest.viewmodel

import androidx.lifecycle.ViewModel
import com.example.naversearchtest.data.remote.NaverApi
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Retrofit
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val apiService: NaverApi) : ViewModel() {

}
