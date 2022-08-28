package com.zizou.marvel.data.repository

import com.zizou.marvel.data.datasource.FavoriteCharacterLocalDataSource
import com.zizou.marvel.data.mapper.FavoriteCharacterMapper
import com.zizou.marvel.domain.model.Character
import com.zizou.marvel.domain.repository.FavoriteCharacterRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

class FavoriteCharacterRepositoryImpl(
    private val dataSource: FavoriteCharacterLocalDataSource
) : FavoriteCharacterRepository {
    override fun getFavoriteCharacters(): Flowable<List<Character>> {
        return dataSource.getFavoriteCharacters()
            .map { it.map { favoriteCharacterEntity ->  FavoriteCharacterMapper.toModel(favoriteCharacterEntity) } }
    }

    override fun addFavoriteCharacter(character: Character): Completable {
        return dataSource.addFavoriteCharacter(FavoriteCharacterMapper.toEntity(character))
    }

    override fun removeFavoriteCharacter(character: Character): Completable {
        return dataSource.removeFavoriteCharacter(FavoriteCharacterMapper.toEntity(character))
    }
}