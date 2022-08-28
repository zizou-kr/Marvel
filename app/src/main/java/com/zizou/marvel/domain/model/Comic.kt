package com.zizou.marvel.domain.model

import java.io.Serializable

data class Comic(
    val id: Int?,
    val digitalId: Int?,
    val title: String?,
    val variantDescription: String?,
    val description: String?,
    val modified: String?,
    val isbn: String?,
    val upc: String?,
    val diamondCode: String?,
    val ean: String?,
    val issn: String?,
    val format: String?,
    val pageCount: Int?,
    val textObjects: List<TextObject>?,
    val resourceURI: String?,
    val urls: List<UrlModel>?,
    val series: Summary?,
    val variants: List<Summary>?,
    val collections: List<Summary>?,
    val collectedIssues: List<Summary>?,
    val dates: List<DateModel>?,
    val prices: List<Price>?,
    val thumbnail: ImageModel?,
    val images: List<ImageModel>?,
    val creators: DataList?,
    val characters: DataList?,
    val stories: DataList?,
    val events: DataList?
) : Serializable {
    fun getContents(): String {
        val texts = textObjects?.map { it.text ?: "" } ?: listOf()
        return texts.joinToString("\n\n")
    }
}