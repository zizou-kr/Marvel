package com.zizou.marvel.domain.usecase

import androidx.paging.PagingData
import com.zizou.marvel.domain.model.Character
import com.zizou.marvel.domain.repository.CharacterRepository
import io.reactivex.rxjava3.core.Flowable

class GetCharactersUseCase(
    private val repository: CharacterRepository
) {
    operator fun invoke(): Flowable<PagingData<Character>> {
        return repository.getCharacters()
    }
}