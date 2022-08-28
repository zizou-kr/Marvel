package com.zizou.marvel.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.zizou.marvel.domain.model.Series

class SeriesDetailViewModel : BaseViewModel() {
    private val _series by lazy { MutableLiveData<Series>(null) }
    val series: LiveData<Series>
        get() = _series

    fun initialize(series: Series) {
        _series.postValue(series)
    }
}