package com.zizou.marvel.data.datasource

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import com.zizou.marvel.data.model.CharacterEntity
import io.reactivex.rxjava3.core.Single

class CharacterPagingSource(
    private val dataSource: CharacterRemoteDataSource
) : RxPagingSource<Int, CharacterEntity>() {
    override fun getRefreshKey(state: PagingState<Int, CharacterEntity>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, CharacterEntity>> {
        val nextPage = params.key ?: 1

        return dataSource.getCharacters(nextPage, COUNT_OF_PAGE)
            .map { itemEntities ->
                LoadResult.Page(
                    data = itemEntities,
                    prevKey = null,
                    nextKey = if (itemEntities.isEmpty()) null else nextPage.plus(1)
                ) as LoadResult<Int, CharacterEntity>
            }
            .onErrorReturn { LoadResult.Error(it) }
    }

    companion object {
        private const val COUNT_OF_PAGE = 20
    }
}