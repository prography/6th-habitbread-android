package com.example.habitbread.data

import com.google.gson.annotations.SerializedName

data class NewHabitRes (
    @SerializedName("habitId")
    val habitId: Int,
    @SerializedName("title")
    val habitName: String
)
