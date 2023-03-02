package com.example.samplelistadapter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.samplelistadapter.adapter.TextListAdapter
import com.example.samplelistadapter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<MainViewModel>()
    private lateinit var binding : ActivityMainBinding

    private val textListAdapter by lazy {
        TextListAdapter(
            onClick = { Log.e("text", it.content)}
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        initView()
    }

    private fun initView(){
        with(binding){
            viewModel = this@MainActivity.viewModel
            lifecycleOwner = this@MainActivity
            rvHomeTextList.apply {
                adapter = textListAdapter
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            }
        }
    }
}