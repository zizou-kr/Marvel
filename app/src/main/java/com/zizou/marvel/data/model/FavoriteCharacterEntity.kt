package com.zizou.marvel.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "favorite_characters")
data class FavoriteCharacterEntity(
    @PrimaryKey val id: Int?,
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
