package com.zizou.marvel.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.rxjava3.cachedIn
import com.zizou.marvel.domain.model.Comic
import com.zizou.marvel.domain.usecase.GetCharacterComicsUseCase
import io.reactivex.rxjava3.kotlin.addTo
import kotlinx.coroutines.ExperimentalCoroutinesApi

class CharacterComicsViewModel(
    private val getCharacterComicsUseCase: GetCharacterComicsUseCase
) : BasePageViewModel() {
    private val _comicsPagingData by lazy { MutableLiveData<PagingData<Comic>>(null) }
    val comicsPagingData: LiveData<PagingData<Comic>>
        get() = _comicsPagingData

    @OptIn(ExperimentalCoroutinesApi::class)
    fun loadData(characterId: Int) {
        getCharacterComicsUseCase.invoke(characterId)
            .cachedIn(viewModelScope)
            .subscribe({
                _comicsPagingData.postValue(it)
            }) {
                processError(it)
            }
            .addTo(disposables)
    }
}