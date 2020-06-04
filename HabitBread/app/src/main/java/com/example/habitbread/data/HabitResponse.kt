package com.example.habitbread.data

import com.google.gson.annotations.SerializedName

data class HabitResponse(
    @SerializedName("habitId")
    val habitId: Int,
    @SerializedName("title")
    val habitName: String,
    @SerializedName("percent")
    val percentage: Int
)