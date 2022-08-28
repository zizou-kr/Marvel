package com.zizou.marvel.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.zizou.marvel.domain.usecase.GetFavoriteCharactersUseCase
import com.zizou.marvel.domain.usecase.RemoveFavoriteCharacterUseCase
import com.zizou.marvel.presentation.model.CharacterUiModel
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject

class FavoriteCharactersViewModel(
    private val getFavoriteCharactersUseCase: GetFavoriteCharactersUseCase,
    private val removeFavoriteCharacterUseCase: RemoveFavoriteCharacterUseCase
) : BaseViewModel() {
    private val _favoriteCharacters by lazy { MutableLiveData<List<CharacterUiModel>>(null) }
    val favoriteCharacters: LiveData<List<CharacterUiModel>>
        get() = _favoriteCharacters

    private val _isSuccessFavoriteRemove: PublishSubject<Boolean> by lazy { PublishSubject.create() }
    val isSuccessFavoriteRemove: Flowable<Boolean>
        get() = _isSuccessFavoriteRemove.toFlowable(BackpressureStrategy.LATEST)

    fun loadData() {
        getFavoriteCharactersUseCase()
            .map { it.map { character -> CharacterUiModel(character).apply { isFavorite.postValue(true) } } }
            .subscribe({
                _favoriteCharacters.postValue(it)
            }) {
                processError(it)
            }
            .addTo(disposables)
    }

    fun onClickFavorite(character: CharacterUiModel) {
        removeFavoriteCharacterUseCase(character.model)
            .subscribeOn(Schedulers.io())
            .subscribe({
                _isSuccessFavoriteRemove.onNext(true)
            }) {
                _isSuccessFavoriteRemove.onNext(false)
            }
            .addTo(disposables)
    }
}