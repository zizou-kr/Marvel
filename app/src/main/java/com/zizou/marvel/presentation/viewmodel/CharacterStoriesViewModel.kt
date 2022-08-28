package com.zizou.marvel.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.rxjava3.cachedIn
import com.zizou.marvel.domain.model.Story
import com.zizou.marvel.domain.usecase.GetCharacterStoriesUseCase
import io.reactivex.rxjava3.kotlin.addTo
import kotlinx.coroutines.ExperimentalCoroutinesApi

class CharacterStoriesViewModel(
    private val getCharacterStoriesUseCase: GetCharacterStoriesUseCase
) : BasePageViewModel() {
    private val _storiesPagingData by lazy { MutableLiveData<PagingData<Story>>(null) }
    val storiesPagingData: LiveData<PagingData<Story>>
        get() = _storiesPagingData

    @OptIn(ExperimentalCoroutinesApi::class)
    fun loadData(characterId: Int) {
        getCharacterStoriesUseCase.invoke(characterId)
            .cachedIn(viewModelScope)
            .subscribe({
                _storiesPagingData.postValue(it)
            }) {
                processError(it)
            }
            .addTo(disposables)
    }
}