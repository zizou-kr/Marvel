package com.zizou.marvel.data.network

import com.zizou.marvel.data.model.DataWrapperEntity
import com.zizou.marvel.data.model.StoryEntity
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface StoryApi {
    @GET("v1/public/characters/{characterId}/stories")
    fun getCharacterStories(
        @Path("characterId") characterId: Int,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Single<DataWrapperEntity<StoryEntity>>
}