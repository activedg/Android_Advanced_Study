package com.example.naversearchtest.ui

import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.naversearchtest.R
import com.example.naversearchtest.adapter.NewsResultListAdapter
import com.example.naversearchtest.base.BaseActivity
import com.example.naversearchtest.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val viewModel by viewModels<MainViewModel>()
    override fun initView() {
        with(binding){
            lifecycleOwner = this@MainActivity
            viewmodel = this@MainActivity.viewModel

            rvNewsResult.apply {
                adapter = NewsResultListAdapter(
                    onClick = {
                        Log.e("newsItemClick", it.toString())
                    }
                )

                layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            }
        }
    }
}
