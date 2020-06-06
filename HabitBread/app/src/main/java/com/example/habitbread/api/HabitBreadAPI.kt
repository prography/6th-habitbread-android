package com.example.habitbread.api

import com.example.habitbread.data.DetailResponse
import com.example.habitbread.data.HabitResponse
import com.example.habitbread.data.RankResponse
import com.example.habitbread.data.NewHabit
import retrofit2.Call
import retrofit2.http.*

interface HabitBreadAPI {
    @GET("/habits")
    fun getAllHabitLists(): Call<List<HabitResponse>>
    @GET("/habits/{habitId}/calendar/{year}/{month}")
    fun getHabitDetail(@Path("habitId") habitId: Int, @Path("year") year: Int, @Path("month") month: Int): Call<DetailResponse>
    @GET("/ranking")
    fun getAllRankings() : Call<RankResponse>
    @POST("/habits")
    fun postNewHabit(@Body body: NewHabit): Call<HabitResponse>
}