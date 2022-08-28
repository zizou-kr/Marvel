package com.zizou.marvel.presentation.model

import androidx.lifecycle.MutableLiveData
import com.zizou.marvel.domain.model.Character
import java.io.Serializable

data class CharacterUiModel(
    val model: Character,
    val isFavorite: MutableLiveData<Boolean> = MutableLiveData(false)
) : Serializable {
}
