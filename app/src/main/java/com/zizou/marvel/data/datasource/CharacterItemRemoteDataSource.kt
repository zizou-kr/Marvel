package com.zizou.marvel.data.datasource

import com.zizou.marvel.data.datasource.remote.BaseRemoteDataSource
import io.reactivex.rxjava3.core.Single

interface CharacterItemRemoteDataSource<T> : BaseRemoteDataSource<T> {
    fun getCharacterItems(id: Int, page: Int, countOfPage: Int): Single<List<T>>
}