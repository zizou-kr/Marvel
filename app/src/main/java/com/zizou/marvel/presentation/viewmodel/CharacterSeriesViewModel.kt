package com.zizou.marvel.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.rxjava3.cachedIn
import com.zizou.marvel.domain.model.Series
import com.zizou.marvel.domain.usecase.GetCharacterSeriesUseCase
import io.reactivex.rxjava3.kotlin.addTo
import kotlinx.coroutines.ExperimentalCoroutinesApi

class CharacterSeriesViewModel(
    private val getCharacterSeriesUseCase: GetCharacterSeriesUseCase
) : BasePageViewModel() {
    private val _seriesPagingData by lazy { MutableLiveData<PagingData<Series>>(null) }
    val seriesPagingData: LiveData<PagingData<Series>>
        get() = _seriesPagingData

    @OptIn(ExperimentalCoroutinesApi::class)
    fun loadData(characterId: Int) {
        getCharacterSeriesUseCase.invoke(characterId)
            .cachedIn(viewModelScope)
            .subscribe({
                _seriesPagingData.postValue(it)
            }) {
                processError(it)
            }
            .addTo(disposables)
    }
}