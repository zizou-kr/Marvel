package com.zizou.marvel.data.model

import java.io.Serializable

data class DataListEntity(
    val available: Int?,
    val collectionURI: String?,
    val items: List<SummaryEntity>?,
    val returned: Int?
) : Serializable