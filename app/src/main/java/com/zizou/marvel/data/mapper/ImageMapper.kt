package com.zizou.marvel.data.mapper

import com.zizou.marvel.data.model.ImageEntity
import com.zizou.marvel.domain.model.ImageModel

object ImageMapper {
    fun toModel(entity: ImageEntity?): ImageModel {
        return ImageModel(
            extension = entity?.extension,
            path = entity?.path
        )
    }

    fun toEntity(model: ImageModel?): ImageEntity {
        return ImageEntity(
            extension = model?.extension,
            path = model?.path
        )
    }
}