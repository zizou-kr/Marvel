package com.zizou.marvel.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import androidx.paging.rxjava3.flowable
import com.zizou.marvel.data.datasource.CharacterPagingSource
import com.zizou.marvel.data.datasource.CharacterRemoteDataSource
import com.zizou.marvel.data.mapper.CharacterMapper
import com.zizou.marvel.domain.model.Character
import com.zizou.marvel.domain.repository.CharacterRepository
import io.reactivex.rxjava3.core.Flowable

class CharacterRepositoryImpl(
    private val dataSource: CharacterRemoteDataSource
) : CharacterRepository {
    override fun getCharacters(): Flowable<PagingData<Character>> {
        return Pager(PagingConfig(pageSize = 1)) { CharacterPagingSource(dataSource) }
            .flowable
            .map { pagingData -> pagingData.map { characterEntity -> CharacterMapper.toModel(characterEntity) }  }
    }
}