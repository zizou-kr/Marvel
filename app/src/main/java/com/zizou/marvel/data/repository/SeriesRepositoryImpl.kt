package com.zizou.marvel.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import androidx.paging.rxjava3.flowable
import com.zizou.marvel.data.datasource.CharacterItemPagingSource
import com.zizou.marvel.data.datasource.SeriesRemoteDataSource
import com.zizou.marvel.data.mapper.SeriesMapper
import com.zizou.marvel.domain.model.Series
import com.zizou.marvel.domain.repository.SeriesRepository
import io.reactivex.rxjava3.core.Flowable

class SeriesRepositoryImpl(
    private val dataSource: SeriesRemoteDataSource
) : SeriesRepository {

    override fun getCharacterSeries(characterId: Int): Flowable<PagingData<Series>> {
        return Pager(PagingConfig(pageSize = 1)) { CharacterItemPagingSource(characterId, dataSource) }
            .flowable
            .map { pagingData -> pagingData.map { eventEntity -> SeriesMapper.toModel(eventEntity) }  }
    }
}