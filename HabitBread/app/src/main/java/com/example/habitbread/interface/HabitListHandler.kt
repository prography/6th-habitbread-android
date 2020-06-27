package com.example.habitbread.`interface`

import com.example.habitbread.data.Habits

interface HabitListHandler {
    fun onResult(handlerList: List<Habits>?)
}