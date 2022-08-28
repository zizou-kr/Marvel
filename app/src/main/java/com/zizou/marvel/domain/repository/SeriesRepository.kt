package com.zizou.marvel.domain.repository

import androidx.paging.PagingData
import com.zizou.marvel.domain.model.Character
import com.zizou.marvel.domain.model.Comic
import com.zizou.marvel.domain.model.Event
import com.zizou.marvel.domain.model.Series
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

interface SeriesRepository {
    fun getCharacterSeries(characterId: Int): Flowable<PagingData<Series>>
}