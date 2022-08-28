package com.zizou.marvel.data.network

import com.zizou.marvel.data.model.CharacterEntity
import com.zizou.marvel.data.model.DataWrapperEntity
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterApi {
    @GET("v1/public/characters")
    fun getCharacters(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Single<DataWrapperEntity<CharacterEntity>>
}