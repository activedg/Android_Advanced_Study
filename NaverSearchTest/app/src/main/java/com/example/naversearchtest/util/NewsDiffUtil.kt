package com.example.naversearchtest.util

import androidx.recyclerview.widget.DiffUtil
import com.example.naversearchtest.domain.entity.NewsItem

object NewsDiffUtil : DiffUtil.ItemCallback<NewsItem>(){
    override fun areItemsTheSame(oldItem: NewsItem, newItem: NewsItem): Boolean {
        return oldItem.originallink == newItem.originallink
    }

    override fun areContentsTheSame(oldItem: NewsItem, newItem: NewsItem): Boolean {
        return oldItem == newItem
    }
}