package com.example.habitbread.data

data class NewHabit (
    val title: String,
    val category: String,
    val dayOfWeek: String,
    val alarmTime: String?
)
