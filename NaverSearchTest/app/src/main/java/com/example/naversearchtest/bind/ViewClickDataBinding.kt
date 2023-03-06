package com.example.naversearchtest.bind

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.example.naversearchtest.util.clicks
import com.example.naversearchtest.util.throttleFirst
import kotlinx.coroutines.flow.*

@BindingAdapter("android:clickThrottle")
inline fun View.clickThrottle(crossinline onClick : () -> Unit)
    = findViewTreeLifecycleOwner()?.lifecycleScope?.let {
        clicks().throttleFirst(1000)
            .onEach { onClick() }
            .launchIn(it)
    }
