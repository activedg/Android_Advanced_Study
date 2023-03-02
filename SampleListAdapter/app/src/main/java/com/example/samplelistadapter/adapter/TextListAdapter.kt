package com.example.samplelistadapter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.samplelistadapter.R
import com.example.samplelistadapter.databinding.ItemTextListBinding
import com.example.samplelistadapter.model.Text

class TextListAdapter(private val onClick: (Text) -> Unit) :
    ListAdapter<Text, TextListAdapter.ViewHolder>(TextDiffUtil)
{
    private lateinit var binding: ItemTextListBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_text_list,
            parent,
            false
        )
        return ViewHolder(onClick, binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        private val onClick: (Text) -> Unit,
        private val binding: ItemTextListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                binding.text?.let(onClick)
            }
        }

        fun bind(text: Text){
            binding.text = text
        }
    }

}