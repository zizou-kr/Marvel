package com.zizou.marvel.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.zizou.marvel.databinding.ItemComicBinding
import com.zizou.marvel.domain.model.Comic

class ComicAdapter(
    private val clickListener: OnItemClickListener,
    diffCallback: DiffUtil.ItemCallback<Comic>
) : PagingDataAdapter<Comic, ComicAdapter.ComicViewHolder>(diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicViewHolder {
        val binding = ItemComicBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ComicViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ComicViewHolder, position: Int) {
        val story = getItem(position)
        holder.bind(story, clickListener)
    }

    class ComicViewHolder(private val binding: ItemComicBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(comic: Comic?, clickListener: OnItemClickListener) {
            comic?.let {
                binding.containerRoot.setOnClickListener { _ -> clickListener.onItemClick(it) }
                binding.comic = it
            }
        }
    }

    class ComicComparator : DiffUtil.ItemCallback<Comic>() {
        override fun areItemsTheSame(oldItem: Comic, newItem: Comic): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Comic, newItem: Comic): Boolean {
            return oldItem == newItem
        }
    }

    interface OnItemClickListener {
        fun onItemClick(comic: Comic)
    }
}