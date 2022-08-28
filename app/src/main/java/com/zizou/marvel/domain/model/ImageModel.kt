package com.zizou.marvel.domain.model

import java.io.Serializable

data class ImageModel(
    val extension: String?,
    val path: String?
) : Serializable {
    fun getThumbnailUrl(): String {
        if (path.isNullOrBlank() || extension.isNullOrBlank()) return ""

        return "$path.$extension"
    }
}
