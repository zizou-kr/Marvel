package com.zizou.marvel.data.model

import java.io.Serializable

data class CreatorEntity(
    val id: Int?,
    val firstName: String?,
    val middleName: String?,
    val lastName: String?,
    val suffix: String?,
    val fullName: String?,
    val modified: String?,
    val thumbnail: ImageEntity?,
    val resourceURI: String?,
    val comics: DataListEntity?,
    val series: DataListEntity?,
    val stories: DataListEntity?,
    val events: DataListEntity?,
    val urls: List<UrlEntity>?
) : Serializable