package com.zizou.marvel.domain.usecase

import com.zizou.marvel.domain.model.Character
import com.zizou.marvel.domain.repository.FavoriteCharacterRepository
import io.reactivex.rxjava3.core.Completable

class AddFavoriteCharacterUseCase(
    private val repository: FavoriteCharacterRepository
) {
    operator fun invoke(character: Character): Completable {
        return repository.addFavoriteCharacter(character)
    }
}