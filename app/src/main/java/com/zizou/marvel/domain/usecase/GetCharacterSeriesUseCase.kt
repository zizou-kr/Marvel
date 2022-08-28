package com.zizou.marvel.domain.usecase

import androidx.paging.PagingData
import com.zizou.marvel.domain.model.Series
import com.zizou.marvel.domain.repository.SeriesRepository
import io.reactivex.rxjava3.core.Flowable

class GetCharacterSeriesUseCase(
    private val repository: SeriesRepository
)  {
    operator fun invoke(characterId: Int): Flowable<PagingData<Series>> {
        return repository.getCharacterSeries(characterId)
    }
}