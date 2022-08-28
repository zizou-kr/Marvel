package com.zizou.marvel.data.mapper

import com.zizou.marvel.data.model.StoryEntity
import com.zizou.marvel.domain.model.Story

object StoryMapper {
    fun toModel(entity: StoryEntity): Story {
        return Story(
            id = entity.id,
            title = entity.title,
            description = entity.description,
            type = entity.type,
            modified = entity.modified,
            thumbnail = ImageMapper.toModel(entity.thumbnail),
            resourceURI = entity.resourceURI,
            originalIssue = SummaryMapper.toModel(entity.originalIssue),
            events = DataListMapper.toModel(entity.events),
            series = DataListMapper.toModel(entity.series),
            characters = DataListMapper.toModel(entity.characters),
            comics = DataListMapper.toModel(entity.comics),
            creators = DataListMapper.toModel(entity.creators)
        )
    }
}