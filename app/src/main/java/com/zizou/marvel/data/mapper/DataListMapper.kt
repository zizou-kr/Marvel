package com.zizou.marvel.data.mapper

import com.zizou.marvel.data.model.DataListEntity
import com.zizou.marvel.domain.model.DataList

object DataListMapper {
    fun toModel(entity: DataListEntity?): DataList {
        return DataList(
            available = entity?.available,
            collectionURI = entity?.collectionURI,
            items = entity?.items?.map { SummaryMapper.toModel(it) },
            returned = entity?.returned
        )
    }

    fun toEntity(model: DataList?): DataListEntity {
        return DataListEntity(
            available = model?.available,
            collectionURI = model?.collectionURI,
            items = model?.items?.map { SummaryMapper.toEntity(it) },
            returned = model?.returned
        )
    }
}