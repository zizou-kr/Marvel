package com.zizou.marvel.domain.model

import java.io.Serializable

data class Price(
    val type: String?,
    val price: Double?
) : Serializable