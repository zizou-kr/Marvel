package com.zizou.marvel.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.zizou.marvel.domain.model.Event

class EventDetailViewModel : BaseViewModel() {
    private val _event by lazy { MutableLiveData<Event>(null) }
    val event: LiveData<Event>
        get() = _event

    fun initialize(event: Event) {
        _event.postValue(event)
    }
}