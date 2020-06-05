package com.example.habitbread.api

import com.example.habitbread.data.DetailResponse
import com.example.habitbread.data.HabitResponse
import retrofit2.Call
import retrofit2.http.*

interface HabitBreadAPI {
    @GET("/habits")
    fun getAllHabitLists(): Call<List<HabitResponse>>

    @GET("/habits/{habitId}/calendar/{year}/{month}")
    fun getHabitDetail(@Path("habitId") habitId: Int, @Path("year") year: Int, @Path("month") month: Int): Call<DetailResponse>
}