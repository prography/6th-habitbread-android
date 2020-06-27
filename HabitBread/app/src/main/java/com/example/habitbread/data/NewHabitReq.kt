package com.example.habitbread.data

data class NewHabitReq (
    val title: String,
    val category: String,
    val dayOfWeek: String,
    val alarmTime: String?
)
