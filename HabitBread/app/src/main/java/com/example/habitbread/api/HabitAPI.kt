package com.example.habitbread.api

import com.example.habitbread.data.Habit
import com.example.habitbread.data.HabitResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.*

interface HabitAPI {
    @GET("/users/:userId/habits")
    fun getHabits(
        callback: Callback<HabitResponse>
    ): Call<HabitResponse>
    @POST("/users/:userId/habits")
    fun createHabit(
        callback: Callback<Habit>
    ): Call<HabitResponse>
    @GET("/users/:userId/habits/%s")
    fun getHabit(
        callback: Callback<Habit>
    ): Call<Habit>
    @PUT("/users/:userId/habits/:habitId")
    fun editHabit(

    ): Call<HabitResponse>
    @DELETE("/users/:userId/habits/:habitId")
    fun deleteHabit(

    ):Call<HabitResponse>

    @GET("/ranking")
    fun getRankings (

    ): Call<HabitResponse>
}