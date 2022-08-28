package com.zizou.marvel.domain.repository

import androidx.paging.PagingData
import com.zizou.marvel.domain.model.Story
import io.reactivex.rxjava3.core.Flowable

interface StoryRepository {
    fun getCharacterStories(characterId: Int): Flowable<PagingData<Story>>
}