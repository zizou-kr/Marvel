package com.zizou.marvel.data.model

import java.io.Serializable


data class CharacterEntity(
    val id: Int?,
    val name: String?,
    val description: String?,
    val urls: List<UrlEntity>?,
    val modified: String?,
    val resourceURI: String?,
    val comics: DataListEntity?,
    val events: DataListEntity?,
    val series: DataListEntity?,
    val stories: DataListEntity?,
    val thumbnail: ImageEntity?
) : Serializable