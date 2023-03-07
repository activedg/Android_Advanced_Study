package com.example.naversearchtest.ui

import android.util.Log
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.naversearchtest.R
import com.example.naversearchtest.adapter.NewsResultListAdapter
import com.example.naversearchtest.base.BaseActivity
import com.example.naversearchtest.databinding.ActivityMainBinding
import com.example.naversearchtest.ui.MainContract.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val viewModel by viewModels<MainViewModel>()
    private val newsAdapter by lazy {
        NewsResultListAdapter(
            onClick = {
                Log.e("newsItemClick", it.toString())
            }
        )
    }
    override fun initView() {
        with(binding){
            mainViewModel = viewModel

            rvNewsResult.apply {
                adapter = newsAdapter
                layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            }

            svKeyword.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.let {
                        viewModel.setEvent(MainEvent.SearchKeyword(it))
                    }
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean = false
            })
        }

        observe()
    }

    private fun observe(){
        viewModel.pagingData.flowWithLifecycle(lifecycle, Lifecycle.State.CREATED).onEach {
            newsAdapter.submitData(it)
        }.launchIn(lifecycleScope)
    }
}
