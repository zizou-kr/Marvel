package com.zizou.marvel.data.datasource.remote

import com.zizou.marvel.data.datasource.ComicRemoteDataSource
import com.zizou.marvel.data.model.ComicEntity
import com.zizou.marvel.data.network.ComicApi
import io.reactivex.rxjava3.core.Single

class ComicRemoteDataSourceImpl(
    private val api: ComicApi
) : ComicRemoteDataSource {
    override fun getCharacterItems(id: Int, page: Int, countOfPage: Int): Single<List<ComicEntity>> {
        val offset = (page - 1) * countOfPage
        return api.getCharacterComics(id, offset, countOfPage)
            .map { checkAndGetResults(it) }
    }
}