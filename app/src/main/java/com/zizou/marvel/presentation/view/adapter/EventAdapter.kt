package com.zizou.marvel.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.zizou.marvel.databinding.ItemEventBinding
import com.zizou.marvel.domain.model.Event

class EventAdapter(
    private val clickListener: OnItemClickListener,
    diffCallback: DiffUtil.ItemCallback<Event>
) : PagingDataAdapter<Event, EventAdapter.EventViewHolder>(diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val binding = ItemEventBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val book = getItem(position)
        holder.bind(book, clickListener)
    }

    class EventViewHolder(private val binding: ItemEventBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(book: Event?, clickListener: OnItemClickListener) {
            book?.let {
                binding.containerRoot.setOnClickListener { _ -> clickListener.onItemClick(it) }
                binding.event = it
            }
        }
    }

    class EventComparator : DiffUtil.ItemCallback<Event>() {
        override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
            return oldItem == newItem
        }
    }

    interface OnItemClickListener {
        fun onItemClick(event: Event)
    }
}