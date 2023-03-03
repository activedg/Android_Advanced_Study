package com.example.naversearchtest.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.naversearchtest.R
import com.example.naversearchtest.databinding.ItemNewsBinding
import com.example.naversearchtest.domain.entity.NewsItem

class NewsResultListAdapter(private val onClick : (NewsItem) -> Unit)
    : PagingDataAdapter<NewsItem,NewsResultListAdapter.ViewHolder>(newsDiffCallback)
{
    private lateinit var binding: ItemNewsBinding
    companion object {
        private val newsDiffCallback = object : DiffUtil.ItemCallback<NewsItem>(){
            override fun areItemsTheSame(oldItem: NewsItem, newItem: NewsItem): Boolean {
                return oldItem.originallink == newItem.originallink
            }

            override fun areContentsTheSame(oldItem: NewsItem, newItem: NewsItem): Boolean {
                return oldItem == newItem
            }
        }
    }

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
        getItem(position)?.let {
            holder.bind(it)
        }
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