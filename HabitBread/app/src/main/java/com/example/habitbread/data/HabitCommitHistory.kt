package com.example.habitbread.data

import com.google.gson.annotations.SerializedName

data class HabitCommitHistory(
    @SerializedName("createdAt")
    val createdAt: String
)