package com.zizou.marvel.presentation.view.adapter

import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.zizou.marvel.databinding.ItemCharacterBinding
import com.zizou.marvel.presentation.model.CharacterUiModel
import com.zizou.marvel.presentation.view.HomeFragmentDirections

class CharacterViewHolder(
    private val binding: ItemCharacterBinding,
    private val listener: FavoriteClickListener
    ) : RecyclerView.ViewHolder(binding.root) {
    fun bind(character: CharacterUiModel?) {
        character?.let {
            binding.containerRoot.setOnClickListener { _ ->
                binding.root.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToCharacterItemsDialogFragment(it.model))
            }
            binding.imageFavoriteActive.setOnClickListener { _ -> listener.onFavoriteClick(it, true) }
            binding.imageFavoriteInactive.setOnClickListener { _ -> listener.onFavoriteClick(it, false) }
            binding.characterUiModel = it
        }
    }
}