package com.zizou.marvel.domain.model

import java.io.Serializable

data class DataList(
    val available: Int?,
    val collectionURI: String?,
    val items: List<Summary>?,
    val returned: Int?
) : Serializable
