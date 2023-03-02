package com.example.samplelistadapter.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.samplelistadapter.model.Text

object TextDiffUtil : DiffUtil.ItemCallback<Text>() {
    override fun areItemsTheSame(oldItem: Text, newItem: Text): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Text, newItem: Text): Boolean {
        return oldItem.id == newItem.id
    }
}