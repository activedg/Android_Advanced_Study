package com.example.naversearchtest.bind

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.naversearchtest.adapter.NewsResultListAdapter
import com.example.naversearchtest.domain.entity.NewsItem

@BindingAdapter("bindList")
fun RecyclerView.bindList(
    list : List<NewsItem>
) {
    this.bindAdapter?.submitList(list)
}

val RecyclerView.bindAdapter : NewsResultListAdapter?
    get() = this.adapter as? NewsResultListAdapter