package com.example.habitbread.data

import com.google.gson.annotations.SerializedName

data class DetailResponse(
    @SerializedName("habit")
    val habit: HabitDetailInfo,
    @SerializedName("commitFullCount")
    val commitFullCount: Int
)



