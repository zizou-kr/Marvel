package com.zizou.marvel.domain.repository

import com.zizou.marvel.domain.model.Character
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

interface FavoriteCharacterRepository {
    fun getFavoriteCharacters(): Flowable<List<Character>>
    fun addFavoriteCharacter(character: Character): Completable
    fun removeFavoriteCharacter(character: Character): Completable
}