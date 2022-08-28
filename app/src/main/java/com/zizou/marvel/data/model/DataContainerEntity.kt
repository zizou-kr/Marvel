package com.zizou.marvel.data.model

import java.io.Serializable

data class DataContainerEntity<T>(
    val offset: Int?,
    val limit: Int?,
    val total: Int?,
    val count: Int?,
    val results: List<T>?
) : Serializable