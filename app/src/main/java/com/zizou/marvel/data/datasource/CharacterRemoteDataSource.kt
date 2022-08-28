package com.zizou.marvel.data.datasource

import com.zizou.marvel.data.model.CharacterEntity
import io.reactivex.rxjava3.core.Single

interface CharacterRemoteDataSource {
    fun getCharacters(page: Int, countOfPage: Int): Single<List<CharacterEntity>>
}