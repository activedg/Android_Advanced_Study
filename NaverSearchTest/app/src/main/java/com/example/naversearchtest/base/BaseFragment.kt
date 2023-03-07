package com.example.naversearchtest.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

abstract class BaseFragment<VB : ViewDataBinding> (@LayoutRes private val layoutRes: Int): Fragment() {
    private var _binding: VB? = null
    protected val binding: VB get() = _binding ?: throw IllegalStateException("_binding is null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        initView()
        observeData()
    }

    abstract fun initView()
    abstract fun observeData()

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    protected inline fun <T: Any> Flow<T>.bind(
        crossinline action: () -> Unit
    ){
        this.onEach {
            action()
        }.launchIn(lifecycleScope)
    }
}