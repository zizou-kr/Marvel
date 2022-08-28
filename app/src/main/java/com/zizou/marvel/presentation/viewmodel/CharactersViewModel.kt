package com.zizou.marvel.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.map
import androidx.paging.rxjava3.cachedIn
import com.zizou.marvel.domain.model.Character
import com.zizou.marvel.domain.usecase.AddFavoriteCharacterUseCase
import com.zizou.marvel.domain.usecase.GetCharactersAndFavoritesUseCase
import com.zizou.marvel.domain.usecase.RemoveFavoriteCharacterUseCase
import com.zizou.marvel.presentation.model.CharacterUiModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import kotlinx.coroutines.ExperimentalCoroutinesApi

class CharactersViewModel(
    private val getCharactersAndFavoritesUseCase: GetCharactersAndFavoritesUseCase,
    private val addFavoriteCharacterUseCase: AddFavoriteCharacterUseCase,
    private val removeFavoriteCharacterUseCase: RemoveFavoriteCharacterUseCase
) : BasePageViewModel() {
    private val _charactersPagingData by lazy { MutableLiveData<PagingData<CharacterUiModel>>(null) }
    val charactersPagingData: LiveData<PagingData<CharacterUiModel>>
        get() = _charactersPagingData

    private val _isSuccessFavoriteAdd: PublishSubject<Boolean> by lazy { PublishSubject.create() }
    val isSuccessFavoriteAdd: Flowable<Boolean>
        get() = _isSuccessFavoriteAdd.toFlowable(BackpressureStrategy.LATEST)
    private val _isSuccessFavoriteRemove: PublishSubject<Boolean> by lazy { PublishSubject.create() }
    val isSuccessFavoriteRemove: Flowable<Boolean>
        get() = _isSuccessFavoriteRemove.toFlowable(BackpressureStrategy.LATEST)

    @OptIn(ExperimentalCoroutinesApi::class)
    fun loadData() {
        getCharactersAndFavoritesUseCase.invoke()
            .map {
                val paging = it.first
                val favoriteIds = it.second.map { favorite -> favorite.id }

                paging.map { character: Character ->
                    val isFavorite = MutableLiveData(favoriteIds.contains(character.id))
                    CharacterUiModel(character, isFavorite)
                }
            }
            .cachedIn(viewModelScope)
            .subscribe({
                _charactersPagingData.postValue(it)
            }) {
                processError(it)
            }
            .addTo(disposables)
    }

    fun onClickFavorite(character: CharacterUiModel) {
        removeFavoriteCharacterUseCase(character.model)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                character.isFavorite.postValue(false)
                _isSuccessFavoriteRemove.onNext(true)
            }) {
                _isSuccessFavoriteRemove.onNext(false)
            }
            .addTo(disposables)
    }

    fun onClickUnFavorite(character: CharacterUiModel) {
        addFavoriteCharacterUseCase(character.model)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                character.isFavorite.postValue(true)
                _isSuccessFavoriteAdd.onNext(true)
            }) {
                _isSuccessFavoriteAdd.onNext(false)
            }
            .addTo(disposables)
    }
}