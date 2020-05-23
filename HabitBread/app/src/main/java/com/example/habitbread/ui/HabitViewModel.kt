package com.example.habitbread.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.habitbread.base.BaseViewModel
import com.example.habitbread.repository.HabitRepository

class HabitViewModel constructor(habitRepository: HabitRepository): BaseViewModel(){
    private val _text = MutableLiveData<String>()
    val text: LiveData<String>
        get() = _text

    init {
        _text.value = "Hello World"
    }
}