package com.zizou.marvel.data.datasource.remote

import com.zizou.marvel.data.datasource.StoryRemoteDataSource
import com.zizou.marvel.data.model.StoryEntity
import com.zizou.marvel.data.network.StoryApi
import io.reactivex.rxjava3.core.Single

class StoryRemoteDataSourceImpl(
    private val api: StoryApi
) : StoryRemoteDataSource {
    override fun getCharacterItems(id: Int, page: Int, countOfPage: Int): Single<List<StoryEntity>> {
        val offset = (page - 1) * countOfPage
        return api.getCharacterStories(id, offset, countOfPage)
            .map { checkAndGetResults(it) }
    }
}