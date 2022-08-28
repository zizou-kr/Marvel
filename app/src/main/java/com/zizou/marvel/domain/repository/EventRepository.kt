package com.zizou.marvel.domain.repository

import androidx.paging.PagingData
import com.zizou.marvel.domain.model.Event
import io.reactivex.rxjava3.core.Flowable

interface EventRepository {
    fun getCharacterEvents(characterId: Int): Flowable<PagingData<Event>>
}