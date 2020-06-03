package com.example.habitbread.ui.viewModel

import com.example.habitbread.base.BaseViewModel
import com.example.habitbread.data.HabitResponse
import com.example.habitbread.repository.HabitRepository

class HabitViewModel : BaseViewModel(){
    private val accessToken = "eyJhbGciOiJIUzM4NCIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjUsImlhdCI6MTU5MTE3MjkxN30.i00yOSVFiHPQ2wa474R46uEVj6cdse29m_S9grl1EvRvCwyY5X7YNWOs_6TlLzL8"
    private val habitListData: List<HabitResponse> = HabitRepository.getAllHabits(accessToken)

    fun getHabitListData(): List<HabitResponse>{
        return habitListData
    }
}