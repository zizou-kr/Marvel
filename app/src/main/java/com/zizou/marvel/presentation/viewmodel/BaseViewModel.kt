package com.zizou.marvel.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.subjects.PublishSubject

open class BaseViewModel : ViewModel() {
    private val _error: PublishSubject<Throwable> by lazy { PublishSubject.create() }
    val error: Flowable<Throwable>
        get() = _error.toFlowable(BackpressureStrategy.LATEST)

    private val _isLoading by lazy { MutableLiveData<Boolean>() }
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    protected val disposables: CompositeDisposable by lazy { CompositeDisposable() }

    protected fun processError(error: Throwable) {
        _error.onNext(error)
    }

    fun onLoadingStateChanged(isLoading: Boolean) {
        _isLoading.postValue(isLoading)
    }

    fun onError(error: Throwable) {
        _error.onNext(error)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}