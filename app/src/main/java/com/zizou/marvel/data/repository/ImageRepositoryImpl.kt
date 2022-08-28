package com.zizou.marvel.data.repository

import com.zizou.marvel.data.datasource.ImageDeviceDataSource
import com.zizou.marvel.domain.repository.ImageRepository
import io.reactivex.rxjava3.core.Single
import java.util.*

class ImageRepositoryImpl(
    private val dataSource: ImageDeviceDataSource
) : ImageRepository {
    override fun downloadImage(url: String): Single<UUID> {
        return dataSource.downloadImage(url)
    }
}