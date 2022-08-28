package com.zizou.marvel.data.model

import java.io.Serializable

data class DataWrapperEntity<T>(
    val code: Int?,
    val status: String?,
    val copyright: String?,
    val attributionText: String?,
    val attributionHTML: String?,
    val etag: String?,
    val data: DataContainerEntity<T>?
) : Serializable