package com.zizou.marvel.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import androidx.paging.rxjava3.flowable
import com.zizou.marvel.data.datasource.CharacterItemPagingSource
import com.zizou.marvel.data.datasource.StoryRemoteDataSource
import com.zizou.marvel.data.mapper.StoryMapper
import com.zizou.marvel.domain.model.Story
import com.zizou.marvel.domain.repository.StoryRepository
import io.reactivex.rxjava3.core.Flowable

class StoryRepositoryImpl(
    private val dataSource: StoryRemoteDataSource
) : StoryRepository {
    override fun getCharacterStories(characterId: Int): Flowable<PagingData<Story>> {
        return Pager(PagingConfig(pageSize = 1)) { CharacterItemPagingSource(characterId, dataSource) }
            .flowable
            .map { pagingData -> pagingData.map { eventEntity -> StoryMapper.toModel(eventEntity) }  }
    }
}