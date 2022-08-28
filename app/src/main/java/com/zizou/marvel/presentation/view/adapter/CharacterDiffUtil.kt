package com.zizou.marvel.presentation.view.adapter

import androidx.recyclerview.widget.DiffUtil
import com.zizou.marvel.presentation.model.CharacterUiModel

class CharacterDiffUtil : DiffUtil.ItemCallback<CharacterUiModel>() {
    override fun areItemsTheSame(oldItem: CharacterUiModel, newItem: CharacterUiModel): Boolean {
        return oldItem.model.id == newItem.model.id
    }

    override fun areContentsTheSame(oldItem: CharacterUiModel, newItem: CharacterUiModel): Boolean {
        return oldItem == newItem
    }
}