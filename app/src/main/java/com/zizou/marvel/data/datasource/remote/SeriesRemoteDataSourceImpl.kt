package com.zizou.marvel.data.datasource.remote

import com.zizou.marvel.data.datasource.SeriesRemoteDataSource
import com.zizou.marvel.data.model.SeriesEntity
import com.zizou.marvel.data.network.SeriesApi
import io.reactivex.rxjava3.core.Single

class SeriesRemoteDataSourceImpl(
    private val api: SeriesApi
) : SeriesRemoteDataSource {
    override fun getCharacterItems(id: Int, page: Int, countOfPage: Int): Single<List<SeriesEntity>> {
        val offset = (page - 1) * countOfPage
        return api.getCharacterSeries(id, offset, countOfPage)
            .map { checkAndGetResults(it) }
    }
}