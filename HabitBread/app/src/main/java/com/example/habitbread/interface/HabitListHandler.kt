package com.example.habitbread.`interface`

import com.example.habitbread.data.HabitResponse

interface HabitListHandler {
    fun onResult(handlerList: List<HabitResponse>)
}