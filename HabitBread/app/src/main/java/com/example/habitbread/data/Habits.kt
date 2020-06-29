package com.example.habitbread.data

import com.google.gson.annotations.SerializedName

data class Habits(
    @SerializedName("habitId")
    val habitId: Int,
    @SerializedName("title")
    val habitName: String,
    @SerializedName("description")
    val description: String?,
    @SerializedName("dayOfWeek")
    val dayOfWeek: String,
    @SerializedName("commitHistory")
    val commitHistory: List<HabitCommitHistory>
)