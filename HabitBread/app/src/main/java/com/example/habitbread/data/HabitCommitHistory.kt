package com.example.habitbread.data

import com.google.gson.annotations.SerializedName
import java.util.*

data class HabitCommitHistory(
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("habitId")
    val habitId: Int
)