package com.zizou.marvel.data.mapper

import com.zizou.marvel.data.model.TextObjectEntity
import com.zizou.marvel.domain.model.TextObject

object TextObjectMapper {
    fun toModel(entity: TextObjectEntity): TextObject {
        return TextObject(
            type =  entity.type,
            language = entity.language,
            text = entity.text
        )
    }
}