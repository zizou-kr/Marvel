package com.zizou.marvel.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.zizou.marvel.domain.model.Character
import com.zizou.marvel.domain.usecase.DownloadImageUseCase
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import java.util.*

class CharacterItemViewModel(
    private val downloadImageUseCase: DownloadImageUseCase
) : BaseViewModel() {
    private val _character: MutableLiveData<Character> by lazy { MutableLiveData() }
    val character: LiveData<Character>
        get() = _character

    private val _isDownloadSuccess: PublishSubject<Boolean> by lazy { PublishSubject.create() }
    val isDownloadSuccess: Flowable<Boolean>
        get() = _isDownloadSuccess.toFlowable(BackpressureStrategy.LATEST)

    private val _workerRequestId: PublishSubject<UUID> by lazy { PublishSubject.create() }
    val workerRequestId: Flowable<UUID>
        get() = _workerRequestId.toFlowable(BackpressureStrategy.LATEST)

    fun initialize(character: Character) {
        _character.postValue(character)
    }

    fun downloadImage() {
        character.value?.let {
            downloadImageUseCase(it.getThumbnailUrl())
                .subscribeOn(Schedulers.io())
                .subscribe({ id ->
                    //_isDownloadSuccess.onNext(true)
                    _workerRequestId.onNext(id)
                }) {
                    _isDownloadSuccess.onNext(false)
                }
                .addTo(disposables)
        }
    }

    fun onDownloadFailed() = _isDownloadSuccess.onNext(false)
    fun onDownloadSuccess() = _isDownloadSuccess.onNext(true)
}