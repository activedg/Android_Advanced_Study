package com.example.naversearchtest.bind

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("android:visibleInvisible")
fun View.setVisibility(visible: Boolean){
    this.visibility = if (visible) View.VISIBLE else View.INVISIBLE
}

@BindingAdapter("android:visibleGone")
fun View.setVisibility2(visible: Boolean){
    this.visibility = if (visible) View.VISIBLE else View.GONE
}