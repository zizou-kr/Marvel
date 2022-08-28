package com.zizou.marvel.data.datasource.local

import com.zizou.marvel.data.database.FavoriteCharacterDao
import com.zizou.marvel.data.datasource.FavoriteCharacterLocalDataSource
import com.zizou.marvel.data.model.FavoriteCharacterEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

class FavoriteCharacterLocalDataSourceImpl(
    private val dao: FavoriteCharacterDao
) : FavoriteCharacterLocalDataSource {
    override fun getFavoriteCharacters(): Flowable<List<FavoriteCharacterEntity>> {
        return dao.getAll()
    }

    override fun addFavoriteCharacter(favoriteCharacter: FavoriteCharacterEntity): Completable {
        return dao.insertAll(favoriteCharacter)
    }

    override fun removeFavoriteCharacter(favoriteCharacter: FavoriteCharacterEntity): Completable {
        return dao.delete(favoriteCharacter)
    }
}