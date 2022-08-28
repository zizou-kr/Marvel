package com.zizou.marvel.domain.usecase

import com.zizou.marvel.domain.model.Character
import com.zizou.marvel.domain.repository.FavoriteCharacterRepository
import io.reactivex.rxjava3.core.Flowable

class GetFavoriteCharactersUseCase(
    private val repository: FavoriteCharacterRepository
) {
    operator fun invoke(): Flowable<List<Character>> {
        return repository.getFavoriteCharacters()
    }
}