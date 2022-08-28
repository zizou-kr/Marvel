package com.zizou.marvel.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import androidx.paging.rxjava3.flowable
import com.zizou.marvel.data.datasource.CharacterItemPagingSource
import com.zizou.marvel.data.datasource.EventRemoteDataSource
import com.zizou.marvel.data.mapper.EventMapper
import com.zizou.marvel.domain.model.Event
import com.zizou.marvel.domain.repository.EventRepository
import io.reactivex.rxjava3.core.Flowable

class EventRepositoryImpl(
    private val dataSource: EventRemoteDataSource
) : EventRepository {
    override fun getCharacterEvents(characterId: Int): Flowable<PagingData<Event>> {
        return Pager(PagingConfig(pageSize = 1)) { CharacterItemPagingSource(characterId, dataSource) }
            .flowable
            .map { pagingData -> pagingData.map { eventEntity -> EventMapper.toModel(eventEntity) }  }
    }
}