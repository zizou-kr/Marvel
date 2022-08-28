package com.zizou.marvel.data.datasource.remote

import com.zizou.marvel.data.datasource.CharacterRemoteDataSource
import com.zizou.marvel.data.model.CharacterEntity
import com.zizou.marvel.data.model.DataWrapperEntity
import com.zizou.marvel.data.network.CharacterApi
import io.reactivex.rxjava3.core.Single
import java.io.IOException

class CharacterRemoteDataSourceImpl(
    private val api: CharacterApi
) : CharacterRemoteDataSource {
    override fun getCharacters(page: Int, countOfPage: Int): Single<List<CharacterEntity>> {
        val offset = (page - 1) * countOfPage
        return api.getCharacters(offset, countOfPage)
            .map { checkItems(it) }
    }

    private fun checkItems(dataWrapper: DataWrapperEntity<CharacterEntity>): List<CharacterEntity> {
        return if (dataWrapper.code == 200) {
            dataWrapper.data?.results ?: listOf()
        } else {
            throw IOException("${dataWrapper.code}, ${dataWrapper.status}")
        }
    }
}