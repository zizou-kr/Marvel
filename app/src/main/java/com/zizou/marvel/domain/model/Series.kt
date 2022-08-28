package com.zizou.marvel.domain.model

import java.io.Serializable

data class Series(
    val id: Int?,
    val title: String?,
    val description: String?,
    val resourceURI: String?,
    val urls: List<UrlModel>?,
    val startYear: Int?,
    val endYear: Int?,
    val rating: String?,
    val type: String?,
    val modified: String?,
    val thumbnail: ImageModel?,
    val creators: DataList?,
    val characters: DataList?,
    val stories: DataList?,
    val comics: DataList?,
    val events: DataList?,
    val next: Summary?,
    val previous: Summary?
) : Serializable {
    fun getPeriod(): String {
        if (startYear == null) return ""

        return "$startYear - $endYear"
    }
}
