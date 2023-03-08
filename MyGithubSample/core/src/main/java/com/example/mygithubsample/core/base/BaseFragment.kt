package com.example.mygithubsample.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<VB : ViewDataBinding> (@LayoutRes private val layoutRes: Int): Fragment() {
    private var _binding: VB? = null
    protected val binding: VB get() = _binding ?: throw IllegalStateException("_binding is null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        initView()
        observe()
    }

    abstract fun initView()
    abstract fun observe()

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}