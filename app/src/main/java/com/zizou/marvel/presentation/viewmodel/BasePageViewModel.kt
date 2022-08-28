package com.zizou.marvel.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.subjects.PublishSubject

open class BasePageViewModel : BaseViewModel() {
    private val _isEndOfPaging: PublishSubject<Boolean> by lazy { PublishSubject.create() }
    val isEndOfPaging: Flowable<Boolean>
        get() = _isEndOfPaging.toFlowable(BackpressureStrategy.LATEST)

    private val _isEmptyData by lazy { MutableLiveData<Boolean>() }
    val isEmptyData: LiveData<Boolean>
        get() = _isEmptyData

    fun onPageLoad(isEndOfPaging: Boolean) {
        _isEmptyData.postValue(false)
        if (isEndOfPaging) _isEndOfPaging.onNext(true)
    }

    fun onPageEmpty() {
        _isEmptyData.postValue(true)
    }
}