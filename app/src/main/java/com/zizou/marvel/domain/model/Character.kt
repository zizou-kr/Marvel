package com.zizou.marvel.domain.model

import java.io.Serializable

data class Character(
    val id: Int?,
    val name: String?,
    val description: String?,
    val modified: String?,
    val resourceURI: String?,
    val thumbnail: ImageModel?,
    val series: DataList?,
    val stories: DataList?,
    val comics: DataList?,
    val events: DataList?,
    val urls: List<UrlModel>?
) : Serializable {
    fun getThumbnailUrl(): String {
        val path = thumbnail?.path
        val extension = thumbnail?.extension

        if (path.isNullOrBlank() || extension.isNullOrBlank()) return ""

        return "$path.$extension"
    }

    fun getWebUrl(): String {
        val wikiUrl = urls?.find { it.type == "wiki" }
        if (wikiUrl != null && !wikiUrl.url.isNullOrBlank()) return wikiUrl.url

        return urls?.get(0)?.url ?: ""
    }

    fun getUrlCount(): Int = urls?.size ?: 0
    fun getSeriesCount(): Int = series?.available ?: 0
    fun getStoryCount(): Int = stories?.available ?: 0
    fun getComicCount(): Int = comics?.available ?: 0
    fun getEventCount(): Int = events?.available ?: 0
}
