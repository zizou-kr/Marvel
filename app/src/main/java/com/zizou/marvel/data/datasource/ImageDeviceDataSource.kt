package com.zizou.marvel.data.datasource

import io.reactivex.rxjava3.core.Single
import java.util.*

interface ImageDeviceDataSource {
    fun downloadImage(url: String): Single<UUID>
}