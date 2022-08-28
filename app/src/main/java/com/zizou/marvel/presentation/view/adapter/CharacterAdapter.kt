package com.zizou.marvel.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.zizou.marvel.databinding.ItemCharacterBinding
import com.zizou.marvel.presentation.model.CharacterUiModel

class CharacterAdapter(
    diffCallback: DiffUtil.ItemCallback<CharacterUiModel>,
    private val listener: FavoriteClickListener
) : PagingDataAdapter<CharacterUiModel, CharacterViewHolder>(diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        binding.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        return CharacterViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val book = getItem(position)
        holder.bind(book)
    }
}