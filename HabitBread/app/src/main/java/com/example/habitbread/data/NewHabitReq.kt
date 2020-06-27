package com.example.habitbread.data

data class NewHabitReq (
    val title: String,
    val category: String,
    val description: String,
    val dayOfWeek: String,
    val alarmTime: String?
)
