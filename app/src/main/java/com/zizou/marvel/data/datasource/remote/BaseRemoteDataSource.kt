package com.zizou.marvel.data.datasource.remote

import com.zizou.marvel.data.model.DataWrapperEntity
import java.io.IOException

interface BaseRemoteDataSource<T> {
    fun checkAndGetResults(dataWrapper: DataWrapperEntity<T>): List<T> {
        return if (dataWrapper.code == 200) {
            dataWrapper.data?.results ?: listOf()
        } else {
            throw IOException("${dataWrapper.code}, ${dataWrapper.status}")
        }
    }

    fun checkAndGetResult(dataWrapper: DataWrapperEntity<T>): T {
        val results = checkAndGetResults(dataWrapper)
        return if (results.isNotEmpty()) {
            results[0]
        } else {
            throw IOException("Data is empty..")
        }
    }
}