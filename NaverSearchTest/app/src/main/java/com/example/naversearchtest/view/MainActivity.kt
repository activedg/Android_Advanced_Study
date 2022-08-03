package com.example.naversearchtest.view

import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.naversearchtest.R
import com.example.naversearchtest.base.BaseActivity
import com.example.naversearchtest.databinding.ActivityMainBinding
import com.example.naversearchtest.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val viewModel by viewModels<MainViewModel>()
    override fun initView() {
        viewModel.searchLiveData.observe(this, Observer {

        })
    }
}
