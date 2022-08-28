package com.zizou.marvel.data.model

import java.io.Serializable

data class EventEntity(
    val id: Int?,
    val title: String?,
    val description: String?,
    val resourceURI: String?,
    val urls: List<UrlEntity>?,
    val modified: String?,
    val start: String?,
    val end: String?,
    val thumbnail: ImageEntity?,
    val creators: DataListEntity?,
    val characters: DataListEntity?,
    val stories: DataListEntity?,
    val comics: DataListEntity?,
    val series: DataListEntity?,
    val next: SummaryEntity?,
    val previous: SummaryEntity?
) : Serializable