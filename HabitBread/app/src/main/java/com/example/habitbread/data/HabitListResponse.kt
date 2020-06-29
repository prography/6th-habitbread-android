package com.example.habitbread.data

import com.google.gson.annotations.SerializedName

data class HabitListResponse(
    @SerializedName("comment")
    val comment: String,
    @SerializedName("habits")
    val habits: List<Habits>
)



