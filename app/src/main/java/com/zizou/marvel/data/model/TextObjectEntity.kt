package com.zizou.marvel.data.model


import java.io.Serializable

data class TextObjectEntity(
    val type: String?,
    val language: String?,
    val text: String?
) : Serializable