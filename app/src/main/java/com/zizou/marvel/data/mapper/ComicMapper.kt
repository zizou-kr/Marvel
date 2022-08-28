package com.zizou.marvel.data.mapper

import com.zizou.marvel.data.model.ComicEntity
import com.zizou.marvel.domain.model.*

object ComicMapper {
    fun toModel(entity: ComicEntity): Comic {
        return Comic(
            id = entity.id,
            digitalId = entity.digitalId,
            title = entity.title,
            variantDescription = entity.variantDescription,
            description = entity.description,
            modified = entity.modified,
            isbn = entity.isbn,
            upc = entity.upc,
            diamondCode = entity.diamondCode,
            ean = entity.ean,
            issn = entity.issn,
            format = entity.format,
            pageCount = entity.pageCount,
            textObjects = entity.textObjects?.map { TextObjectMapper.toModel(it) },
            resourceURI = entity.resourceURI,
            urls = entity.urls?.map { UrlMapper.toModel(it) },
            series = SummaryMapper.toModel(entity.series),
            variants = entity.variants?.map { SummaryMapper.toModel(it) },
            collections = entity.collections?.map { SummaryMapper.toModel(it) },
            collectedIssues = entity.collectedIssues?.map { SummaryMapper.toModel(it) },
            dates = entity.dates?.map { DateMapper.toModel(it) },
            prices = entity.prices?.map { PriceMapper.toModel(it) },
            thumbnail = ImageMapper.toModel(entity.thumbnail),
            images = entity.images?.map { ImageMapper.toModel(it) },
            creators = DataListMapper.toModel(entity.creators),
            characters = DataListMapper.toModel(entity.characters),
            stories = DataListMapper.toModel(entity.stories),
            events = DataListMapper.toModel(entity.events),
        )
    }
}