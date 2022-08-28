package com.zizou.marvel.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.zizou.marvel.databinding.ItemCharacterBinding
import com.zizou.marvel.presentation.model.CharacterUiModel

class FavoriteCharacterAdapter(
    diffCallback: DiffUtil.ItemCallback<CharacterUiModel>,
    private val listener: FavoriteClickListener
) : ListAdapter<CharacterUiModel, CharacterViewHolder>(diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val book = getItem(position)
        holder.bind(book)
    }
}