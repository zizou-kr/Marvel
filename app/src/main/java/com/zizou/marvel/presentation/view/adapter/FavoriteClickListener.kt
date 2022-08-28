package com.zizou.marvel.presentation.view.adapter

import com.zizou.marvel.presentation.model.CharacterUiModel

interface FavoriteClickListener {
    fun onFavoriteClick(character: CharacterUiModel, isFavorite: Boolean)
}