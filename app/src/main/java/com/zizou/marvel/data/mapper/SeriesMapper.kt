package com.zizou.marvel.data.mapper

import com.zizou.marvel.data.model.SeriesEntity
import com.zizou.marvel.domain.model.*

object SeriesMapper {
    fun toModel(entity: SeriesEntity): Series {
        return Series(
            id = entity.id,
            title = entity.title,
            description = entity.description,
            resourceURI = entity.resourceURI,
            urls = entity.urls?.map { UrlMapper.toModel(it) },
            startYear = entity.startYear,
            endYear = entity.endYear,
            rating = entity.rating,
            type = entity.type,
            modified = entity.modified,
            thumbnail = ImageMapper.toModel(entity.thumbnail),
            creators = DataListMapper.toModel(entity.creators),
            characters = DataListMapper.toModel(entity.characters),
            stories = DataListMapper.toModel(entity.characters),
            comics = DataListMapper.toModel(entity.comics),
            events = DataListMapper.toModel(entity.events),
            next = SummaryMapper.toModel(entity.next),
            previous = SummaryMapper.toModel(entity.previous)
        )
    }
}