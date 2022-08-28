package com.zizou.marvel.data.datasource.remote

import com.zizou.marvel.data.datasource.EventRemoteDataSource
import com.zizou.marvel.data.model.EventEntity
import com.zizou.marvel.data.network.EventApi
import io.reactivex.rxjava3.core.Single

class EventRemoteDataSourceImpl(
    private val api: EventApi
) : EventRemoteDataSource {
    override fun getCharacterItems(id: Int, page: Int, countOfPage: Int): Single<List<EventEntity>> {
        val offset = (page - 1) * countOfPage
        return api.getCharacterEvents(id, offset, countOfPage)
            .map { checkAndGetResults(it) }
    }
}