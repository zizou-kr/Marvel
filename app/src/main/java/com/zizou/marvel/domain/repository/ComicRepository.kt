package com.zizou.marvel.domain.repository

import androidx.paging.PagingData
import com.zizou.marvel.domain.model.Character
import com.zizou.marvel.domain.model.Comic
import com.zizou.marvel.domain.model.Event
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

interface ComicRepository {
    fun getCharacterComics(characterId: Int): Flowable<PagingData<Comic>>
}