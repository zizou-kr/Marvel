package com.zizou.marvel.data.mapper

import com.zizou.marvel.data.model.CharacterEntity
import com.zizou.marvel.data.model.FavoriteCharacterEntity
import com.zizou.marvel.domain.model.Character

object FavoriteCharacterMapper {
    fun toModel(entity: FavoriteCharacterEntity): Character {
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

    fun toEntity(model: Character): FavoriteCharacterEntity {
        return FavoriteCharacterEntity(
            id = model.id,
            name = model.name,
            description = model.description,
            modified = model.modified,
            resourceURI = model.resourceURI,
            thumbnail = ImageMapper.toEntity(model.thumbnail),
            series = DataListMapper.toEntity(model.series),
            stories = DataListMapper.toEntity(model.stories),
            comics = DataListMapper.toEntity(model.comics),
            events = DataListMapper.toEntity(model.events),
            urls = model.urls?.map { UrlMapper.toEntity(it) }
        )
    }
}