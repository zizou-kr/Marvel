package com.zizou.marvel.data.datasource

import com.zizou.marvel.data.model.FavoriteCharacterEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

interface FavoriteCharacterLocalDataSource {
    fun getFavoriteCharacters(): Flowable<List<FavoriteCharacterEntity>>
    fun addFavoriteCharacter(favoriteCharacter: FavoriteCharacterEntity): Completable
    fun removeFavoriteCharacter(favoriteCharacter: FavoriteCharacterEntity): Completable
}