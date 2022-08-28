package com.zizou.marvel.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.zizou.marvel.data.model.FavoriteCharacterEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

@Dao
interface FavoriteCharacterDao {
    @Query("SELECT * FROM favorite_characters")
    fun getAll(): Flowable<List<FavoriteCharacterEntity>>

    @Insert
    fun insertAll(vararg favoriteCharacter: FavoriteCharacterEntity): Completable

    @Delete
    fun delete(favoriteCharacter: FavoriteCharacterEntity): Completable
}