package com.zizou.marvel.domain.repository

import androidx.paging.PagingData
import com.zizou.marvel.domain.model.Character
import io.reactivex.rxjava3.core.Flowable

interface CharacterRepository {
    fun getCharacters(): Flowable<PagingData<Character>>
}