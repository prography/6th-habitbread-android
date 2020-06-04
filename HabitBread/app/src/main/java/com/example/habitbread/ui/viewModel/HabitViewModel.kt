package com.example.habitbread.ui.viewModel

import com.example.habitbread.base.BaseViewModel
import com.example.habitbread.data.HabitResponse
import com.example.habitbread.repository.HabitRepository

class HabitViewModel : BaseViewModel(){
    private lateinit var habitListData: List<HabitResponse>

    fun getHabitListData(): List<HabitResponse>{
        habitListData = HabitRepository().getAllHabits()
        return habitListData
    }
}