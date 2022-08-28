package com.zizou.marvel.data.model

import java.io.Serializable

data class SummaryEntity(
    val name: String?,
    val resourceURI: String?,
    val type: String?,
    val role: String?
) : Serializable