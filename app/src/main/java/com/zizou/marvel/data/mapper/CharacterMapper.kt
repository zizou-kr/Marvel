package com.zizou.marvel.data.mapper

import com.zizou.marvel.data.model.CharacterEntity
import com.zizou.marvel.domain.model.Character

object CharacterMapper {
    fun toModel(entity: CharacterEntity): Character {
        return Character(
            id = entity.id,
            name = entity.name,
            description = entity.description,
            modified = entity.modified,
            resourceURI = entity.resourceURI,
            thumbnail = ImageMapper.toModel(entity.thumbnail),
            series = DataListMapper.toModel(entity.series),
            stories = DataListMapper.toModel(entity.stories),
            comics = DataListMapper.toModel(entity.comics),
            events = DataListMapper.toModel(entity.events),
            urls = entity.urls?.map { UrlMapper.toModel(it) }
        )
    }
}