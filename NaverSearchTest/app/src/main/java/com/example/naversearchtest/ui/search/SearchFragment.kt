package com.example.naversearchtest.ui.search

import android.content.Context
import android.view.inputmethod.EditorInfo
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.naversearchtest.R
import com.example.naversearchtest.base.BaseFragment
import com.example.naversearchtest.bind.clickThrottle
import com.example.naversearchtest.databinding.FragmentSearchBinding
import com.example.naversearchtest.ui.search.SearchContract.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {
    private val viewModel by viewModels<SearchViewModel>()

    override fun initView() {
        with(binding) {
            searchViewModel = viewModel

            sbKeyword.clickThrottle {
                viewModel.setEvent(SearchEvent.OnSearchBarClick)
            }

//            requireActivity().onBackPressedDispatcher.addCallback(this@SearchFragment) {
//                if (svKeyword.isShowing) {
//                    binding.svKeyword.hide()
//                } else {
//                    activity?.finish()
//                }
//            }
//
//            svKeyword.editText.setOnEditorActionListener { v, actionId, event ->
//                when (actionId) {
//                    EditorInfo.IME_ACTION_DONE -> {
//                        return@setOnEditorActionListener true
//                    }
//                    else -> {
//                        viewModel.setEvent(SearchEvent.SearchKeyword(svKeyword.text.toString()))
//                        svKeyword.hide()
//                        return@setOnEditorActionListener false
//                    }
//                }
//            }
        }
    }

    override fun observe() {
        viewModel.sideEffect.flowWithLifecycle(lifecycle).onEach {
            when (it) {
                is SearchSideEffect.NavigateToResult -> {
                    val action = SearchFragmentDirections.actionSearchFragmentToSearchResultFragment()
                    findNavController().navigate(action)
                }
            }
        }.launchIn(lifecycleScope)
    }
}