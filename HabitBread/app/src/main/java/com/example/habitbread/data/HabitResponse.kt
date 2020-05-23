package com.example.habitbread.data

data class HabitResponse(
    val habits: List<Habit>
)

data class HabitSingleResponse(
    val habit: Habit
)