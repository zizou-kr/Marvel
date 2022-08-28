package com.zizou.marvel.domain.model

import java.io.Serializable

data class Story(
    val id: Int?,
    val title: String?,
    val description: String?,
    val type: String?,
    val modified: String?,
    val thumbnail: ImageModel?,
    val resourceURI: String?,
    val originalIssue: Summary?,
    val events: DataList?,
    val series: DataList?,
    val characters: DataList?,
    val comics: DataList?,
    val creators: DataList?
) : Serializable