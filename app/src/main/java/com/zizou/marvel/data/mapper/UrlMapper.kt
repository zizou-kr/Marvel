package com.zizou.marvel.data.mapper

import com.zizou.marvel.data.model.UrlEntity
import com.zizou.marvel.domain.model.UrlModel

object UrlMapper {
    fun toModel(entity: UrlEntity): UrlModel {
        return UrlModel(
            type = entity.type,
            url = entity.url
        )
    }

    fun toEntity(model: UrlModel): UrlEntity {
        return UrlEntity(
            type = model.type,
            url = model.url
        )
    }
}