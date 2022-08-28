package com.zizou.marvel.data.network

import com.zizou.marvel.data.model.ComicEntity
import com.zizou.marvel.data.model.DataWrapperEntity
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ComicApi {
    @GET("v1/public/characters/{characterId}/comics")
    fun getCharacterComics(
        @Path("characterId") characterId: Int,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Single<DataWrapperEntity<ComicEntity>>
}