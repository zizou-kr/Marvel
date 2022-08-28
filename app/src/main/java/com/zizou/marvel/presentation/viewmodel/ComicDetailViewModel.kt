package com.zizou.marvel.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.zizou.marvel.domain.model.Comic

class ComicDetailViewModel : BaseViewModel() {
    private val _comic by lazy { MutableLiveData<Comic>(null) }
    val comic: LiveData<Comic>
        get() = _comic

    fun initialize(comic: Comic) {
        _comic.postValue(comic)
    }
}