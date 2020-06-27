package com.example.habitbread.data

import com.google.gson.annotations.SerializedName

data class HabitDetailInfo(
    @SerializedName("habitId")
    val habitId: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("continuousCount")
    val continuousCount: Int,
    @SerializedName("commitHistory")
    val commitHistory: List<HabitCommitHistory>
)