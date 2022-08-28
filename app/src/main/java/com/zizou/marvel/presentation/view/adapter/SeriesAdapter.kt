package com.zizou.marvel.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.zizou.marvel.databinding.ItemSeriesBinding
import com.zizou.marvel.domain.model.Series

class SeriesAdapter(
    private val clickListener: OnItemClickListener,
    diffCallback: DiffUtil.ItemCallback<Series>
) : PagingDataAdapter<Series, SeriesAdapter.SeriesViewHolder>(diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesViewHolder {
        val binding = ItemSeriesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SeriesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SeriesViewHolder, position: Int) {
        val series = getItem(position)
        holder.bind(series, clickListener)
    }

    class SeriesViewHolder(private val binding: ItemSeriesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(series: Series?, clickListener: OnItemClickListener) {
            series?.let {
                binding.containerRoot.setOnClickListener { _ -> clickListener.onItemClick(it) }
                binding.series = it
            }
        }
    }

    class SeriesComparator : DiffUtil.ItemCallback<Series>() {
        override fun areItemsTheSame(oldItem: Series, newItem: Series): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Series, newItem: Series): Boolean {
            return oldItem == newItem
        }
    }

    interface OnItemClickListener {
        fun onItemClick(series: Series)
    }
}