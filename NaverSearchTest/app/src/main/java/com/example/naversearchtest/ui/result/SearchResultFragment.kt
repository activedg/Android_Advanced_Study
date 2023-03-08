package com.example.naversearchtest.ui.result

import android.util.Log
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.naversearchtest.R
import com.example.naversearchtest.adapter.NewsResultListAdapter
import com.example.naversearchtest.base.BaseFragment
import com.example.naversearchtest.databinding.FragmentSearchResultBinding
import com.example.naversearchtest.ui.result.SearchResultContract.ResultEvent
import com.example.naversearchtest.ui.result.SearchResultContract.ResultState
import com.google.android.material.search.SearchView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class SearchResultFragment : BaseFragment<FragmentSearchResultBinding>(R.layout.fragment_search_result) {
    private val viewModel by viewModels<SearchResultViewModel>()

    private val newsAdapter by lazy {
        NewsResultListAdapter(
            onClick = {
                Log.e("newsItemClick", it.toString())
            }
        )
    }

    override fun initView() {
        with(binding){
            resultViewModel = viewModel

            rvNewsResult.apply {
                adapter = newsAdapter
                layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            }

            svKeywordResult.apply {
                // 초기 상태에서 이전 방향키를 누른 경우 화면이 꺼지도록 함
                addTransitionListener { _, previousState, newState ->
                    if (viewModel.viewState.value is ResultState.Initial &&
                        previousState == SearchView.TransitionState.SHOWN &&
                        newState == SearchView.TransitionState.HIDING
                    ) {
                        findNavController().popBackStack()
                    }
                }

                // 엔터 버튼 입력 시 이벤트 처리
                editText.setOnEditorActionListener { _, actionId, _ ->
                    when (actionId) {
                        EditorInfo.IME_ACTION_DONE -> {
                            return@setOnEditorActionListener true
                        }
                        else -> {
                            viewModel.setEvent(ResultEvent.SearchKeyword(svKeywordResult.text.toString()))
                            svKeywordResult.hide()
                            return@setOnEditorActionListener false
                        }
                    }
                }
            }

        }
    }

    override fun observe() {
        viewModel.viewState.flowWithLifecycle(lifecycle).onEach {
            when(it){
                is ResultState.Initial -> {
                    binding.svKeywordResult.show()
                }
                else -> { }
            }
        }.launchIn(lifecycleScope)

        viewModel.pagingData.flowWithLifecycle(lifecycle).onEach {
            newsAdapter.submitData(it)
        }.launchIn(lifecycleScope)
    }
}