package com.zizou.marvel.domain.usecase

import androidx.paging.PagingData
import com.zizou.marvel.domain.model.Character
import io.reactivex.rxjava3.core.Flowable

class GetCharactersAndFavoritesUseCase(
    private val charactersUseCase: GetCharactersUseCase,
    private val favoriteCharactersUseCase: GetFavoriteCharactersUseCase
) {
    operator fun invoke(): Flowable<Pair<PagingData<Character>, List<Character>>> {
        return Flowable.zip(charactersUseCase(), favoriteCharactersUseCase()) { characters, favoriteCharacters ->
            Pair<PagingData<Character>, List<Character>>(characters, favoriteCharacters)
        }
    }
}