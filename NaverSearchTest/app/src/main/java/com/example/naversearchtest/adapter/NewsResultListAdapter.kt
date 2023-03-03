package com.example.naversearchtest.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.naversearchtest.R
import com.example.naversearchtest.databinding.ItemNewsBinding
import com.example.naversearchtest.domain.entity.NewsItem
import com.example.naversearchtest.util.NewsDiffUtil

class NewsResultListAdapter(private val onClick : (NewsItem) -> Unit)
    : ListAdapter<NewsItem,NewsResultListAdapter.ViewHolder>(NewsDiffUtil)
{
    private lateinit var binding: ItemNewsBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_news,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(newsItem: NewsItem){
            with(binding){
                news = newsItem
                tvNewsDate.text = newsItem.pubDate.dropLast(15)
                tvNewsItemLink.paintFlags = Paint.UNDERLINE_TEXT_FLAG

                root.setOnClickListener { onClick(newsItem) }
            }
        }
    }
}