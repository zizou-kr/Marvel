package com.zizou.marvel.data.network

import com.zizou.marvel.data.model.DataWrapperEntity
import com.zizou.marvel.data.model.EventEntity
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface EventApi {
    @GET("v1/public/characters/{characterId}/events")
    fun getCharacterEvents(
        @Path("characterId") characterId: Int,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Single<DataWrapperEntity<EventEntity>>
}