package com.example.habitbread.api

import com.example.habitbread.data.HabitResponse
import com.example.habitbread.data.RankResponse
import retrofit2.Call
import retrofit2.http.*

interface HabitBreadAPI {
    @GET("/habits")
    fun getAllHabitLists(): Call<List<HabitResponse>>
    @GET("/ranking")
    fun getAllRankings() : Call<RankResponse>
}