package com.zizou.marvel.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.zizou.marvel.domain.model.Story

class StoryDetailViewModel : BaseViewModel() {
    private val _story by lazy { MutableLiveData<Story>(null) }
    val story: LiveData<Story>
        get() = _story

    fun initialize(story: Story) {
        _story.postValue(story)
    }
}