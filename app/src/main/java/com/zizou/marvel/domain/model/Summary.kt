package com.zizou.marvel.domain.model

import java.io.Serializable

data class Summary(
    val name: String?,
    val resourceURI: String?,
    val type: String?,
    val role: String?
) : Serializable
