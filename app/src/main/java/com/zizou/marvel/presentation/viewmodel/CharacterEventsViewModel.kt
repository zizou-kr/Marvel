package com.zizou.marvel.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.rxjava3.cachedIn
import com.zizou.marvel.domain.model.Event
import com.zizou.marvel.domain.usecase.GetCharacterEventsUseCase
import io.reactivex.rxjava3.kotlin.addTo
import kotlinx.coroutines.ExperimentalCoroutinesApi

class CharacterEventsViewModel(
    private val getCharacterEventsUseCase: GetCharacterEventsUseCase
) : BasePageViewModel() {
    private val _eventsPagingData by lazy { MutableLiveData<PagingData<Event>>(null) }
    val eventsPagingData: LiveData<PagingData<Event>>
        get() = _eventsPagingData

    @OptIn(ExperimentalCoroutinesApi::class)
    fun loadData(characterId: Int) {
        getCharacterEventsUseCase.invoke(characterId)
            .cachedIn(viewModelScope)
            .subscribe({
                _eventsPagingData.postValue(it)
            }) {
                processError(it)
            }
            .addTo(disposables)
    }
}