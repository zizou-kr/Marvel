package com.zizou.marvel.domain.model

import java.io.Serializable

data class Event(
    val id: Int?,
    val title: String?,
    val description: String?,
    val resourceURI: String?,
    val urls: List<UrlModel>?,
    val modified: String?,
    val start: String?,
    val end: String?,
    val thumbnail: ImageModel?,
    val creators: DataList?,
    val characters: DataList?,
    val stories: DataList?,
    val comics: DataList?,
    val series: DataList?,
    val next: Summary?,
    val previous: Summary?
) : Serializable
