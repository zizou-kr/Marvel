package com.zizou.marvel.domain.usecase

import androidx.paging.PagingData
import com.zizou.marvel.domain.model.Event
import com.zizou.marvel.domain.repository.EventRepository
import io.reactivex.rxjava3.core.Flowable

class GetCharacterEventsUseCase(
    private val repository: EventRepository
) {
    operator fun invoke(characterId: Int): Flowable<PagingData<Event>> {
        return repository.getCharacterEvents(characterId)
    }
}