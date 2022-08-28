package com.zizou.marvel.data.model

import java.io.Serializable

data class ComicEntity(
    val id: Int?,
    val digitalId: Int?,
    val title: String?,
    val variantDescription: String?,
    val description: String?,
    val modified: String?,
    val isbn: String?,
    val upc: String?,
    val diamondCode: String?,
    val ean: String?,
    val issn: String?,
    val format: String?,
    val pageCount: Int?,
    val textObjects: List<TextObjectEntity>?,
    val resourceURI: String?,
    val urls: List<UrlEntity>?,
    val series: SummaryEntity?,
    val variants: List<SummaryEntity>?,
    val collections: List<SummaryEntity>?,
    val collectedIssues: List<SummaryEntity>?,
    val dates: List<DateEntity>?,
    val prices: List<PriceEntity>?,
    val thumbnail: ImageEntity?,
    val images: List<ImageEntity>?,
    val creators: DataListEntity?,
    val characters: DataListEntity?,
    val stories: DataListEntity?,
    val events: DataListEntity?
) : Serializable