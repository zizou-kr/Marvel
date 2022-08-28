package com.zizou.marvel.data.mapper

import com.zizou.marvel.data.model.PriceEntity
import com.zizou.marvel.domain.model.Price

object PriceMapper {
    fun toModel(entity: PriceEntity): Price {
        return Price(
            type = entity.type,
            price = entity.price
        )
    }
}