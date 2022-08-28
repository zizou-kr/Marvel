package com.zizou.marvel.data.model

import java.io.Serializable

data class SeriesEntity(
    val id: Int?,
    val title: String?,
    val description: String?,
    val resourceURI: String?,
    val urls: List<UrlEntity>?,
    val startYear: Int?,
    val endYear: Int?,
    val rating: String?,
    val type: String?,
    val modified: String?,
    val thumbnail: ImageEntity?,
    val creators: DataListEntity?,
    val characters: DataListEntity?,
    val stories: DataListEntity?,
    val comics: DataListEntity?,
    val events: DataListEntity?,
    val next: SummaryEntity?,
    val previous: SummaryEntity?
) : Serializable