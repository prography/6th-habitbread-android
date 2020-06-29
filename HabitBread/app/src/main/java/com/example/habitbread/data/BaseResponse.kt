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
    val createdAt: String
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

data class NewHabit(
    @SerializedName("title")
    val title: String,
    @SerializedName("category")
    val category: String,
    @SerializedName("dayOfWeek")
    val dayOfWeek: String,
    @SerializedName("alarmTime")
    val alarmTime: String?
)

data class Ranking(
    @SerializedName("userId")
    val userId : Int,
    @SerializedName("userName")
    val userName : String,
    @SerializedName("exp")
    val exp: Int,
    @SerializedName("achievement")
    val achievement : Int,
    @SerializedName("rank")
    val rank : String
)

data class RankResponse(
    @SerializedName("user")
    val user : Ranking,
    @SerializedName("userTotalCount")
    val userTotalCount: Int,
    @SerializedName("rankings")
    val rankings : List<Ranking>
)

data class TempRequest(
    @SerializedName("idToken")
    val idToken: String
)

data class TempResponse(
    @SerializedName("accessToken")
    val idToken: String,
    @SerializedName("name")
    val name : String
)

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

data class CommitResponse(
    @SerializedName("itemId")
    val itemId: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("level")
    val level: Int?
)

data class HabitListResponse(
    @SerializedName("comment")
    val comment: String,
    @SerializedName("habits")
    val habits: List<Habits>
)

data class NewHabitRes(
    @SerializedName("habitId")
    val habitId: Int,
    @SerializedName("title")
    val habitName: String
)

data class NewHabitReq(
    val title: String,
    val category: String,
    val description: String,
    val dayOfWeek: String,
    val alarmTime: String?
)