package com.zizou.marvel.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import androidx.paging.rxjava3.flowable
import com.zizou.marvel.data.datasource.CharacterItemPagingSource
import com.zizou.marvel.data.datasource.ComicRemoteDataSource
import com.zizou.marvel.data.mapper.ComicMapper
import com.zizou.marvel.domain.model.Comic
import com.zizou.marvel.domain.repository.ComicRepository
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

class ComicRepositoryImpl(
    private val dataSource: ComicRemoteDataSource
) : ComicRepository {
    override fun getCharacterComics(characterId: Int): Flowable<PagingData<Comic>> {
        return Pager(PagingConfig(pageSize = 1)) { CharacterItemPagingSource(characterId, dataSource) }
            .flowable
            .map { pagingData -> pagingData.map { eventEntity -> ComicMapper.toModel(eventEntity) }  }
    }
}