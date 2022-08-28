package com.zizou.marvel.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.zizou.marvel.databinding.ItemStoryBinding
import com.zizou.marvel.domain.model.Story

class StoryAdapter(
    private val clickListener: OnItemClickListener,
    diffCallback: DiffUtil.ItemCallback<Story>
) : PagingDataAdapter<Story, StoryAdapter.StoryViewHolder>(diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
        val binding = ItemStoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
        val story = getItem(position)
        holder.bind(story, clickListener)
    }

    class StoryViewHolder(private val binding: ItemStoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(story: Story?, clickListener: OnItemClickListener) {
            story?.let {
                binding.containerRoot.setOnClickListener { _ -> clickListener.onItemClick(it) }
                binding.story = it
            }
        }
    }

    class StoryComparator : DiffUtil.ItemCallback<Story>() {
        override fun areItemsTheSame(oldItem: Story, newItem: Story): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Story, newItem: Story): Boolean {
            return oldItem == newItem
        }
    }

    interface OnItemClickListener {
        fun onItemClick(story: Story)
    }
}