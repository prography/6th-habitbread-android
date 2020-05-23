package com.example.habitbread.ui

import androidx.databinding.BaseObservable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.habitbread.base.BaseViewModel
import com.example.habitbread.repository.SampleRepository

class HabitViewModel constructor(sampleRepository: SampleRepository): BaseViewModel(){
    private val _text = MutableLiveData<String>()
    val text: LiveData<String>
        get() = _text

    init {
        _text.value = "Hello World"
    }
}