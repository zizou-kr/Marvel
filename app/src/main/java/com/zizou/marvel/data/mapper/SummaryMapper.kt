package com.zizou.marvel.data.mapper

import com.zizou.marvel.data.model.SummaryEntity
import com.zizou.marvel.domain.model.Summary

object SummaryMapper {
    fun toModel(entity: SummaryEntity?): Summary {
        return Summary(
            name = entity?.name,
            resourceURI = entity?.resourceURI,
            type = entity?.type,
            role = entity?.role
        )
    }

    fun toEntity(model: Summary?): SummaryEntity {
        return SummaryEntity(
            name = model?.name,
            resourceURI = model?.resourceURI,
            type = model?.type,
            role = model?.role
        )
    }
}