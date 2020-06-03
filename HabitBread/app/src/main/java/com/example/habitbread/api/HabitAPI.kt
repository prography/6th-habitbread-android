package com.example.habitbread.api

import com.example.habitbread.data.HabitResponse
import retrofit2.Call
import retrofit2.http.*

interface HabitAPI {
    @GET("/habits")
    fun getAllHabitLists(@Header("Authorization") accessToken: String): Call<List<HabitResponse>>
}