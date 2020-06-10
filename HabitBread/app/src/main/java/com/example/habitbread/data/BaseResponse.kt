package com.example.habitbread.data

import com.google.gson.annotations.SerializedName

data class DetailResponse(
    @SerializedName("habit")
    val habit: HabitDetailInfo,
    @SerializedName("commitFullCount")
    val commitFullCount: Int
)

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

data class HabitCommitHistory(
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("habitId")
    val habitId: Int
)

data class HabitResponse(
    @SerializedName("habitId")
    val habitId: Int,
    @SerializedName("title")
    val habitName: String,
    @SerializedName("percent")
    val percentage: Int
)

data class BaseResponse(
    @SerializedName("message")
    val message: String
)