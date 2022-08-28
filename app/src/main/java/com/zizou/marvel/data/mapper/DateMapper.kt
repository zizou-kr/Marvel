package com.zizou.marvel.data.mapper

import com.zizou.marvel.data.model.DateEntity
import com.zizou.marvel.domain.model.DateModel

object DateMapper {
    fun toModel(entity: DateEntity): DateModel {
        return DateModel(
            type = entity.type,
            date = entity.date
        )
    }
}