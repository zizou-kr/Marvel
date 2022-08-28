package com.zizou.marvel.data.mapper

import com.zizou.marvel.data.model.EventEntity
import com.zizou.marvel.domain.model.*

object EventMapper {
    fun toModel(entity: EventEntity): Event {
        return Event(
            id = entity.id,
            title = entity.title,
            description = entity.description,
            resourceURI = entity.resourceURI,
            urls = entity.urls?.map { UrlMapper.toModel(it) },
            modified = entity.modified,
            start = entity.start,
            end = entity.end,
            thumbnail = ImageMapper.toModel(entity.thumbnail),
            creators = DataListMapper.toModel(entity.creators),
            characters = DataListMapper.toModel(entity.characters),
            stories = DataListMapper.toModel(entity.stories),
            comics = DataListMapper.toModel(entity.comics),
            series = DataListMapper.toModel(entity.series),
            next = SummaryMapper.toModel(entity.next),
            previous = SummaryMapper.toModel(entity.next)
        )
    }
}