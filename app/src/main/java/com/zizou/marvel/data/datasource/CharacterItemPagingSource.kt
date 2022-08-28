package com.zizou.marvel.data.datasource

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import io.reactivex.rxjava3.core.Single

class CharacterItemPagingSource<T : Any>(
    private val id: Int,
    private val dataSource: CharacterItemRemoteDataSource<T>
) : RxPagingSource<Int, T>() {
    override fun getRefreshKey(state: PagingState<Int, T>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, T>> {
        val nextPage = params.key ?: 1

        return dataSource.getCharacterItems(id, nextPage, COUNT_OF_PAGE)
            .map { itemEntities ->
                LoadResult.Page(
                    data = itemEntities,
                    prevKey = null,
                    nextKey = if (itemEntities.isEmpty()) null else nextPage.plus(1)
                ) as LoadResult<Int, T>
            }
            .onErrorReturn { LoadResult.Error(it) }
    }

    companion object {
        private const val COUNT_OF_PAGE = 20
    }
}