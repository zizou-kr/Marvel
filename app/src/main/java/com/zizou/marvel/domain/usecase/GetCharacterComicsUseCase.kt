package com.zizou.marvel.domain.usecase

import androidx.paging.PagingData
import com.zizou.marvel.domain.model.Comic
import com.zizou.marvel.domain.repository.ComicRepository
import io.reactivex.rxjava3.core.Flowable

class GetCharacterComicsUseCase(
    private val repository: ComicRepository
) {
    operator fun invoke(characterId: Int): Flowable<PagingData<Comic>> {
        return repository.getCharacterComics(characterId)
    }
}