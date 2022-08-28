package com.zizou.marvel.data.model

import java.io.Serializable

data class PriceEntity(
    val type: String?,
    val price: Double?
) : Serializable