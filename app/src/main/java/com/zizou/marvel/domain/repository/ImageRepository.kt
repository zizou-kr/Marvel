package com.zizou.marvel.domain.repository

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import java.util.*

interface ImageRepository {
    fun downloadImage(url: String): Single<UUID>
}