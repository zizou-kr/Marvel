package com.zizou.marvel.data.model

import java.io.Serializable

data class StoryEntity(
    val id: Int?,
    val title: String?,
    val description: String?,
    val type: String?,
    val modified: String?,
    val thumbnail: ImageEntity?,
    val resourceURI: String?,
    val originalIssue: SummaryEntity?,
    val events: DataListEntity?,
    val series: DataListEntity?,
    val characters: DataListEntity?,
    val comics: DataListEntity?,
    val creators: DataListEntity?
) : Serializable