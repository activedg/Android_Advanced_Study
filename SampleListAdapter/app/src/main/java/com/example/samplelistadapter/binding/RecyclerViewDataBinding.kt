package com.example.samplelistadapter.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.samplelistadapter.adapter.TextListAdapter
import com.example.samplelistadapter.model.Text

@BindingAdapter("android:list")
fun RecyclerView.bindList(
    items: List<Text>?
){
    this.bindAdapter?.submitList(items)
}

val RecyclerView.bindAdapter : TextListAdapter?
    get() = this.adapter as? TextListAdapter