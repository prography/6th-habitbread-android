package com.example.habitbread.api

import com.example.habitbread.data.RetrofitResponse
import retrofit2.Call
import retrofit2.http.*

interface HabitAPI {
    @GET("/users/:userId/habits")
    fun getHabits(

    ): Call<RetrofitResponse>
    @POST("/users/:userId/habits")
    fun createHabit(

    ): Call<RetrofitResponse>
    @GET("/users/:userId/habits/%s")
    fun getHabit(

    ): Call<RetrofitResponse>
    @PUT("/users/:userId/habits/:habitId")
    fun editHabit(

    ): Call<RetrofitResponse>
    @DELETE("/users/:userId/habits/:habitId")
    fun deleteHabit(

    ):Call<RetrofitResponse>

    @GET("/ranking")
    fun getRankings (

    ): Call<RetrofitResponse>
}