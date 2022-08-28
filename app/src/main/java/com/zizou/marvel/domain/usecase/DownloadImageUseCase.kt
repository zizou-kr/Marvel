package com.zizou.marvel.domain.usecase

import com.zizou.marvel.domain.repository.ImageRepository
import io.reactivex.rxjava3.core.Single
import java.util.*

class DownloadImageUseCase(
    private val repository: ImageRepository
) {
    operator fun invoke(url: String): Single<UUID> {
        return repository.downloadImage(url)
    }
}