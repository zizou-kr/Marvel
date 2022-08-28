package com.zizou.marvel.domain.usecase

import androidx.paging.PagingData
import com.zizou.marvel.domain.model.Story
import com.zizou.marvel.domain.repository.StoryRepository
import io.reactivex.rxjava3.core.Flowable

class GetCharacterStoriesUseCase(
    private val repository: StoryRepository
) {
    operator fun invoke(characterId: Int): Flowable<PagingData<Story>> {
        return repository.getCharacterStories(characterId)
    }
}